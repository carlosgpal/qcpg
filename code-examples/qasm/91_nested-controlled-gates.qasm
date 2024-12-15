OPENQASM 3.0;
gate double_controlled_x q1, q2, q3 {
    cx q1, q2;
    cx q2, q3;
}
qubit q[3];
double_controlled_x q[0], q[1], q[2];
