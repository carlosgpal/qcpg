OPENQASM 3.0;
qubit q[2];
gate cx_gate q1, q2 { cx q1, q2; }
cx_gate q[0], q[1];
