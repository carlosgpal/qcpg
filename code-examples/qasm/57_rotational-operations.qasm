OPENQASM 3.0;
qubit q;
float[32] theta = pi();
rx(theta) q;
rz(theta / 2) q;
