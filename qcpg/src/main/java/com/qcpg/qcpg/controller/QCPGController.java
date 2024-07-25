package com.qcpg.qcpg.controller;

import com.qcpg.qcpg.entities.QCPG;
import com.qcpg.qcpg.service.QCPGService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/qcpg")
public class QCPGController {

    @Value("${custom.uploads}")
    private String uploadsroute;

    @Autowired
    private QCPGService qcpgService;

    @PostMapping("/create")
    public List<QCPG> createQCPG(@RequestBody String path) {
        try {
            return qcpgService.createAndSaveQCPG(uploadsroute + path);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @PostMapping("/upload")
    public List<String> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            return qcpgService.uploadFiles(files);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/{id}")
    public QCPG getQCPG(@PathVariable Long id) {
        return qcpgService.getQCPGById(id);
    }
}
