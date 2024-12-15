OPENQASM 3.0;
function custom_func(float[32] x, int y) {
    return x * y;
}
float[32] result = custom_func(1.57, 2);
