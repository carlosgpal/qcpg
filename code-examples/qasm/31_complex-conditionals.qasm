OPENQASM 3.0;
bit c[1];
qubit q;
c[0] = 0;
if (c[0] == 0) {
    h q;
} else {
    x q;
}
