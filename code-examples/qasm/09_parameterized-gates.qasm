OPENQASM 3.0;
qubit q;
gate rx_pi q {
    rx(3.14159) q;
}
rx_pi q;
