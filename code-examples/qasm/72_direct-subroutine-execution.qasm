OPENQASM 3.0;
def my_subroutine q {
    h q;
    measure q -> c;
}
qubit q;
bit c;
my_subroutine q;
