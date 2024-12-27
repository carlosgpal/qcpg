OPENQASM 3.0;
bit[1] c;
qubit q;
c[0] = 0;
if (c[0] == 0) {
    h q;
} else {
    x q;
}
