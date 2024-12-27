OPENQASM 3.0;
bit[1] a;
a[0] = 0;
int i;
for i in [0:4] {
    a[0] += i;
}
