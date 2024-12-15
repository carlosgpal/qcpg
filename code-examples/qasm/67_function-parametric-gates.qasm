OPENQASM 3.0;
function rx_gate(float[32] theta, qubit q) {
    rx(theta) q;
}
qubit q;
rx_gate(pi(), q);
