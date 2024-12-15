OPENQASM 3.0;
// Missing colon in for-loop declaration
qubit q[3];
for int i in [0 3] {
    h q[i];
}
