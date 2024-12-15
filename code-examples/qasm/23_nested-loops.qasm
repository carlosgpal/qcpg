OPENQASM 3.0;
qubit q[3];
for int i in [0:3] {
    for int j in [0:i] {
        h q[j];
    }
}
