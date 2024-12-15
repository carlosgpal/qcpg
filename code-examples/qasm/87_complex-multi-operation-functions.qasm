OPENQASM 3.0;
function complex_function(float[32] a, float[32] b) {
    float[32] sum = a + b;
    return sin(sum);
}
float[32] result = complex_function(pi() / 4, pi() / 3);
