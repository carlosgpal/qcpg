#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_entanglement = QuantumCircuit(3, 3)
for i in range(3):
    repeated_entanglement.h(i)
    if i < 2:
        repeated_entanglement.cx(i, i + 1)
repeated_entanglement.measure([0, 1, 2], [0, 1, 2])
