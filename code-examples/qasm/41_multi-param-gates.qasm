OPENQASM 3.0;
gate param_gate q, float[32] alpha, float[32] beta {
    rx(alpha) q;
    rz(beta) q;
}
qubit q;
param_gate q, 1.57, 3.14;
