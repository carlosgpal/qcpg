OPENQASM 3.0;
qubit[3] q;
bit[3] c;
h q[0];
measure q[0] -> c[0];
if (c[0] == 1) {
    x q[0];
}
