OPENQASM 3.0;

qubit[5] q;
bit[4] c;

h q[0];
cx q[0], q[1];
x q[2];
y q[3];
z q[4];
s q[0];
sdg q[1];
t q[2];
tdg q[3];
rx(pi/4) q[4];
ry(pi/3) q[0];
rz(pi/2) q[1];
sx q[2];
sxdg q[3];
swap q[0], q[1];
cswap q[0], q[1], q[2];
ccx q[0], q[1], q[2];
iswap q[3], q[4];
cz q[0], q[1];
cy q[2], q[3];
crz(pi/3) q[0], q[4];
crx(pi/4) q[1], q[3];
cry(pi/6) q[2], q[4];
ch q[0], q[2];
cu3(pi/2, pi/3, pi/4) q[3], q[4];
cu1(pi/5) q[0], q[1];
rxx(pi/3) q[0], q[4];
ryy(pi/2) q[1], q[2];
rzz(pi) q[3], q[4];

measure q[0] -> c[0];
measure q[1] -> c[1];

reset q[0];
reset q[1];
reset q;

barrier q[0], q[1], q[2];
barrier q[0];
barrier q;