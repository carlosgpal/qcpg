OPENQASM 3.0;
gate custom_gate q {
    h q;
    if (measure q -> c == 1) {
        x q;
    }
}
qubit q;
bit c;
custom_gate q;
