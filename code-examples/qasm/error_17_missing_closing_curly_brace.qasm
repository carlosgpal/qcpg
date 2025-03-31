OPENQASM 3.0;
// Missing closing curly brace in block
qubit q[2];
{
    h q[0];
    cx q[0], q[1];
