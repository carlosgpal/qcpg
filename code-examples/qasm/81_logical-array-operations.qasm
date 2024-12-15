OPENQASM 3.0;
array[3] int data = {1, 0, 1};
if (data[0] == 1 || data[1] == 1) {
    print("At least one value at 1");
}
