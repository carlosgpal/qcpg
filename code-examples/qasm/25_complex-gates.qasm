OPENQASM 3.0;
gate complex_gate q, r {
    h q;
    cx q, r;
    x q;
}
qubit q[2];
complex_gate q[0], q[1];
