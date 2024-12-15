OPENQASM 3.0;
array[2,2] int matrix = {{1, 2}, {3, 4}};
for int i in [0:2] {
    for int j in [0:2] {
        print(matrix[i, j]);
    }
}
