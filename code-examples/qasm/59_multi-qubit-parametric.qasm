OPENQASM 3.0;
gate multi_param_gate q, float[32] alpha, float[32] beta {
    h q;
    rx(alpha) q;
    rz(beta) q;
}
qubit q[3];
multi_param_gate q[0], 1.57, 0.78;
multi_param_gate q[1], 3.14, 2.71;
