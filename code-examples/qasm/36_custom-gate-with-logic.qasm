OPENQASM 3.0;

def my_complex_gate(bit c, qubit[3] q) {
    if (c == 1) {
        h q[1];
    }
    cx q[1], q[2];
}

qubit[3] q;
bit[1] c;

h q[0];
measure q[0] -> c[0];

my_complex_gate(c[0], q);
