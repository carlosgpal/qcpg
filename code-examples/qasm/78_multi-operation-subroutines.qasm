OPENQASM 3.0;
def quantum_logic q {
    h q;
    x q;
    measure q -> c;
}
qubit q[2];
bit c[2];
quantum_logic q[0];
quantum_logic q[1];
