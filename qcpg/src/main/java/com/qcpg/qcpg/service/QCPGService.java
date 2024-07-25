package com.qcpg.qcpg.service;

import com.qcpg.qcpg.entities.QCPG;
import com.qcpg.qcpg.entities.QuantumProgram;
import com.qcpg.qcpg.repository.GenericNodeRepository;
// import com.qcpg.qcpg.repository.QCPGRepository;
import com.qcpg.qcpg.transformation.QCPGTransformer;
import com.qcpg.qcpg.analysis.QASMParser;
import com.qcpg.qcpg.analysis.QiskitParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QCPGService {

    @Value("${custom.uploads}")
    private String uploadsroute;

    @Autowired
    private QCPGTransformer qcpgTransformer;

    // @Autowired
    // private QCPGRepository qcpgRepository;

    @Autowired
    private GenericNodeRepository genericNodeRepository;

    @Autowired
    private QASMParser qasmParser;

    @Autowired
    private QiskitParser qiskitParser;

    @Transactional
    public List<QCPG> createAndSaveQCPG(String path) throws IOException {
        List<QCPG> qcpgList = new ArrayList<>();
        Path givenPath = Paths.get(path);

        if (Files.isDirectory(givenPath)) {
            try (Stream<Path> paths = Files.walk(givenPath)) {
                paths.filter(Files::isRegularFile).forEach(filePath -> {
                    try {
                        QCPG qcpg = processFile(filePath);
                        qcpgList.add(qcpg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } else if (Files.isRegularFile(givenPath)) {
            QCPG qcpg = processFile(givenPath);
            qcpgList.add(qcpg);
        } else {
            throw new IllegalArgumentException("Provided path is neither a file nor a directory: " + path);
        }

        return qcpgList;
    }

    private QCPG processFile(Path filePath) throws IOException {
        String codeType = filePath.toString().substring(filePath.toString().lastIndexOf('.') + 1);
        QuantumProgram quantumProgram;

        if ("qasm".equalsIgnoreCase(codeType)) {
            quantumProgram = qasmParser.parse(filePath.toString());
        } else if ("py".equalsIgnoreCase(codeType)) {
            quantumProgram = qiskitParser.parse(filePath.toString());
        } else {
            throw new IllegalArgumentException("Unsupported code type: " + codeType);
        }

        QCPG qcpg = qcpgTransformer.transform(quantumProgram);
        genericNodeRepository.saveAll(qcpg.getNodes());

        return qcpg;
    }

    public List<String> uploadFiles(MultipartFile[] files) throws IOException {
        List<String> uploadedFilePaths = new ArrayList<>();

        if (files.length > 1) {
            String folderName = "upload_" + System.currentTimeMillis();
            Path folderPath = Paths.get(uploadsroute).resolve(folderName);
            Files.createDirectories(folderPath);

            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Path destinationFile = folderPath.resolve(Paths.get(file.getOriginalFilename()))
                            .normalize().toAbsolutePath();
                    file.transferTo(destinationFile);
                    uploadedFilePaths.add(destinationFile.toString());
                }
            }
        } else if (files.length == 1) {
            MultipartFile file = files[0];
            if (!file.isEmpty()) {
                Path destinationFile = Paths.get(uploadsroute).resolve(Paths.get(file.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                file.transferTo(destinationFile);
                uploadedFilePaths.add(destinationFile.toString());
            }
        }

        return uploadedFilePaths;
    }

    public QCPG getQCPGById(Long id) {
        // Descomentar cuando se implemente cassandra
        // return qcpgRepository.findById(id).orElse(null);
        return null;
    }
}
