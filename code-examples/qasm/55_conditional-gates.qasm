OPENQASM 3.0;
gate conditional_gate q {
    if (q == 1) {
        x q;
    }
    h q;
}
qubit q[1];
conditional_gate q[0];
