OPENQASM 3.0;
gate my_gate(float[32] theta) qubit q {
    rx(theta) q;
    h q;
}
qubit q;
my_gate(3.14) q;
