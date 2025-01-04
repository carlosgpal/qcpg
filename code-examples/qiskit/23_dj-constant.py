#!/usr/bin/env python3

from qiskit import QuantumCircuit

dj_constant = QuantumCircuit(2, 1)
dj_constant.h([0, 1])
dj_constant.h(0)
dj_constant.measure(0, 0)
