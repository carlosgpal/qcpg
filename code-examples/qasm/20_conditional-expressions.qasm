OPENQASM 3.0;
bit c[1];
qubit q[1];
h q[0];
measure q[0] -> c[0];
if (c[0] == 1) x q[0];
