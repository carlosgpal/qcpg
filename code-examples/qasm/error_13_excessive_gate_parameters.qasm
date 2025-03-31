OPENQASM 3.0;
// Excessive parameters provided to a gate
gate custom_gate q, float[32] a {
    rx(a) q;
}
qubit q;
custom_gate q, 1.57, 3.14;
