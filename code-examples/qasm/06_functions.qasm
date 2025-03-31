OPENQASM 3.0;
gate custom_gate(theta) q {
    rx(theta) q;
}
qubit q;
custom_gate(1.5708) q;