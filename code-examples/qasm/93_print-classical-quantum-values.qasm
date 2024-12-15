OPENQASM 3.0;
qubit q;
bit c;
h q;
measure q -> c;
print("C value: ", c);
