OPENQASM 3.0;
function complex_values(float[32] a) {
    return {a, a * 2, a * 3};
}
array[3] float[32] results = complex_values(1.57);
