OPENQASM 3.0;
def measure_and_reset q {
    measure q -> c;
    reset q;
}
qubit q;
bit c;
measure_and_reset q;
