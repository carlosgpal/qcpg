OPENQASM 3.0;
def my_complex_gate q {
    if (q[0] == 1) h q[1];
    cx q[1], q[2];
}
qubit q[3];
my_complex_gate q;
