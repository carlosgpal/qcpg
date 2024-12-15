OPENQASM 3.0;
gate outer_gate q {
    h q;
    gate inner_gate r {
        x r;
        rz(pi/2) r;
    }
    inner_gate q;
}
qubit q;
outer_gate q;
