OPENQASM 3.0;
bit[1] a;
a[0] = 0;

for int[32] i in [0:4] {
    a[0] = bool(i % 2 == 0);
}