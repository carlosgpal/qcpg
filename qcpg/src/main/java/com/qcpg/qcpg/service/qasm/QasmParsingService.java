package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Service;

/**
 * Service for parsing QASM 3.0 code into an Abstract Syntax Tree (AST).
 * Uses ANTLR to tokenize and parse the input QASM code.
 */
@Service
public class QasmParsingService {

    /**
     * Parses the QASM 3.0 content into an ANTLR ProgramContext.
     * Validates the syntax and throws an exception if errors are found.
     *
     * @param qasmContent The QASM 3.0 code as a string.
     * @return The parsed ProgramContext representing the AST root.
     * @throws Exception If syntax errors are detected in the QASM code.
     */
    public qasm3Parser.ProgramContext parseContent(String qasmContent) throws Exception {
        // Create a character stream from the input QASM code.
        CharStream input = CharStreams.fromString(qasmContent);

        // Initialize the lexer with the character stream.
        qasm3Lexer lexer = new qasm3Lexer(input);

        // Tokenize the input using the lexer.
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Initialize the parser with the token stream.
        qasm3Parser parser = new qasm3Parser(tokens);

        // Check for syntax errors.
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new Exception("The file has syntax errors on OpenQASM 3.0.");
        }

        // Return the root context of the parsed program.
        return parser.program();
    }
}
