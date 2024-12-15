OPENQASM 3.0;
qubit q[2];
for int i in [0:2] {
    if (i == 1) {
        x q[i];
    } else {
        h q[i];
    }
}
