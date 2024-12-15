OPENQASM 3.0;
qubit q[4];
int start = 1;
int end = 3;
for int i in [start:end] {
    x q[i];
}
