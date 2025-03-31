OPENQASM 3.0;
qubit[4] q;
for int i in [0:4] {
    h q[i];
    if (i == 2) {
        break;
    }
}
