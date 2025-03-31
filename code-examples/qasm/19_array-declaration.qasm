OPENQASM 3.0;
creg c[2];
qreg q[2];
c[0] = measure q[0];
c[1] = measure q[1];
