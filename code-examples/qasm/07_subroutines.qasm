OPENQASM 3.0;
def my_gate(qubit q) {
    h q;
    x q;
}
qubit[1] q;
my_gate q[0];
