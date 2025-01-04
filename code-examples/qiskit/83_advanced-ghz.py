#!/usr/bin/env python3

from qiskit import QuantumCircuit

advanced_ghz = QuantumCircuit(5, 5)
advanced_ghz.h(0)
for i in range(4):
    advanced_ghz.cx(i, i + 1)
advanced_ghz.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])
