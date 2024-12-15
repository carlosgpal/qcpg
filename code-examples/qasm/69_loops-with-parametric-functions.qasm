OPENQASM 3.0;
qubit q[5];
for int i in [0:5] {
    rx(i * pi / 5) q[i];
}
