OPENQASM 3.0;
def my_gate q {
    h q;
    x q;
}
qubit q[1];
my_gate q[0];
