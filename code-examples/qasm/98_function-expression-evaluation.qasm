OPENQASM 3.0;
function evaluate_expression(float[32] x) {
    return sin(x) + cos(x);
}
float[32] result = evaluate_expression(pi() / 3);
