OPENQASM 3.0;
qubit q[2];
bit c[2];
h q[0];
cx q[0], q[1];
measure q -> c;
