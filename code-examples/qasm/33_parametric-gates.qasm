OPENQASM 3.0;
gate my_gate q, float[32] theta {
    rx(theta) q;
    h q;
}
qubit q;
my_gate q, 3.14;
