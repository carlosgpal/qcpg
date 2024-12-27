OPENQASM 3.0;
bit[1] c;
qubit[1] q;
h q[0];
measure q[0] -> c[0];
if (c[0] == 1) x q[0];