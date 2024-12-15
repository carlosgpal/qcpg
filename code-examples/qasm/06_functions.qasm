OPENQASM 3.0;
function custom_gate(float[32] theta) {
    rx(theta) q;
}
qubit q;
custom_gate(1.5708);
