OPENQASM 3.0;
gate param_gate(float[32] alpha, float[32] beta) qubit q {
    rx(alpha) q;
    rz(beta) q;
}
qubit q;
param_gate(1.57, 3.14) q;
