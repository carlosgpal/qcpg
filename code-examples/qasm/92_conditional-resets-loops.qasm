OPENQASM 3.0;
qubit q[4];
for int i in [0:4] {
    if (i % 2 == 0) {
        reset q[i];
    }
}
