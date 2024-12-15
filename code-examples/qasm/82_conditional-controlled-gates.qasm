OPENQASM 3.0;
gate controlled_x q1, q2 {
    if (measure q1 -> c == 1) {
        x q2;
    }
}
qubit q[2];
bit c;
controlled_x q[0], q[1];
