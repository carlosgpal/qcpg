OPENQASM 3.0;

const int start = 1;
const int endw = 3;

qubit[4] q;

for int i in [start:endw] {
    x q[i];
}
