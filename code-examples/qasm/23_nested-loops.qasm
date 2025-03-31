OPENQASM 3.0;
qubit[3] q;
for int i in [0:2] {
    for int j in [0:i] {
        h q[j];
    }
}
