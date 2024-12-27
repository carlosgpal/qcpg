OPENQASM 3.0;
gate complex_gate(qubit q, qubit r) {
    h q;
    cx q, r;
    x q;
}
qubit[2] q;
complex_gate q[0], q[1];
