package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

@Service
public class QasmParsingService {

    public qasm3Parser.ProgramContext parseContent(String qasmContent) throws Exception {
        CharStream input = CharStreams.fromString(qasmContent);
        qasm3Lexer lexer = new qasm3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        qasm3Parser parser = new qasm3Parser(tokens);
        return parser.program();
    }
}
