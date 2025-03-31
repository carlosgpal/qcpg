#!/usr/bin/env python3

from qiskit import QuantumCircuit

dj_balanced = QuantumCircuit(3, 1)
dj_balanced.h([0, 1])
dj_balanced.cx(0, 2)
dj_balanced.cx(1, 2)
dj_balanced.h(0)
dj_balanced.measure(0, 0)

dj_constant = QuantumCircuit(3, 1)
dj_constant.h([0, 1])
dj_constant.h(0)
dj_constant.measure(0, 0)
