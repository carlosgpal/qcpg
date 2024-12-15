OPENQASM 3.0;
// Invalid conditional syntax (missing parentheses)
qubit q[1];
if q[0] == 1 {
    x q[0];
}
