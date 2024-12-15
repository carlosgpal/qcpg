OPENQASM 3.0;
gate custom_gate q, float[32] angle {
    if (angle > 1.57) {
        rx(angle) q;
    } else {
        rz(angle) q;
    }
}
qubit q;
custom_gate q, 3.14;
