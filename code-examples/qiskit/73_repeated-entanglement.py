#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_entanglement = QuantumCircuit(4, 4)
for i in range(3):
    repeated_entanglement.cx(i, i + 1)
repeated_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])
