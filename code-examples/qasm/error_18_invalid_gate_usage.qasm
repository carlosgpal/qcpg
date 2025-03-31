OPENQASM 3.0;
// Using a gate before its definition
qubit q[1];
custom_gate q[0];
gate custom_gate q {
    h q;
}
