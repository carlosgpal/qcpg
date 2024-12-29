package com.qcpg.qcpg.service.qasm;

import com.qcpg.qcpg.antlr4.qasm3Lexer;
import com.qcpg.qcpg.antlr4.qasm3Parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
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
        // Convert the QASM code into a character stream
        CharStream input = CharStreams.fromString(qasmContent);

        // Initialize the lexer with the character stream
        qasm3Lexer lexer = new qasm3Lexer(input);

        // Tokenize the input using the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Initialize the parser with the token stream
        qasm3Parser parser = new qasm3Parser(tokens);

        // Add a custom error listener to capture syntax errors
        SyntaxErrorListener errorListener = new SyntaxErrorListener();
        parser.removeErrorListeners(); // Remove default error listeners
        parser.addErrorListener(errorListener); // Add custom error listener

        // Parse the QASM code into a ProgramContext
        qasm3Parser.ProgramContext context = parser.program();

        // Throw an exception if syntax errors were detected
        if (errorListener.hasErrors()) {
            throw new Exception("Syntax errors detected: " + errorListener.getErrors());
        }

        return context; // Return the parsed ProgramContext
    }

    /**
     * Custom error listener to capture and store syntax errors during parsing.
     */
    private static class SyntaxErrorListener extends BaseErrorListener {
        private final List<String> errors = new ArrayList<>(); // List to store error messages

        /**
         * Captures syntax errors during parsing.
         *
         * @param recognizer         The parser instance.
         * @param offendingSymbol    The symbol that caused the error.
         * @param line               The line number where the error occurred.
         * @param charPositionInLine The position in the line where the error occurred.
         * @param msg                The error message.
         * @param e                  The exception representing the error (if any).
         */
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                int line, int charPositionInLine, String msg, RecognitionException e) {
            // Format and store the error message
            errors.add("line " + line + ":" + charPositionInLine + " " + msg);
        }

        /**
         * Checks if any syntax errors were captured.
         *
         * @return True if errors were detected, false otherwise.
         */
        public boolean hasErrors() {
            return !errors.isEmpty();
        }

        /**
         * Retrieves the list of captured syntax errors.
         *
         * @return A list of syntax error messages.
         */
        public List<String> getErrors() {
            return errors;
        }
    }
}
