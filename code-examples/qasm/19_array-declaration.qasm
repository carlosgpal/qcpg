OPENQASM 3.0;
array[2] bit c;
qubit q[2];
c[0] = measure q[0];
c[1] = measure q[1];
