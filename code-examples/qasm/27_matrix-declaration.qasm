OPENQASM 3.0;

bit[32] values;
values[0] = 1.0;
values[1] = 2.0;
values[2] = 3.0;
values[3] = 4.0;

bit[32] matrix;
matrix[0] = 1;
matrix[1] = 2;
matrix[2] = 3;
matrix[3] = 4;

float value = values[2];
int element = matrix[1];
