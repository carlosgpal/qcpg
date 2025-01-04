#!/usr/bin/env python3

from qiskit import QuantumCircuit

dj_balanced = QuantumCircuit(2, 1)
dj_balanced.h([0, 1])
dj_balanced.cx(0, 1)
dj_balanced.h(0)
dj_balanced.measure(0, 0)
