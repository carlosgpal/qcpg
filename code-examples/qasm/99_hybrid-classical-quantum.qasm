OPENQASM 3.0;
qubit q[3];
bit c[3];
for int i in [0:3] {
    h q[i];
    measure q[i] -> c[i];
    if (c[i] == 1) {
        x q[i];
    }
}
