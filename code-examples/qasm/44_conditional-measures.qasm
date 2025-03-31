OPENQASM 3.0;
qubit q;
bit c;
h q;
measure q -> c;
if (c == 0) {
    z q;
}
