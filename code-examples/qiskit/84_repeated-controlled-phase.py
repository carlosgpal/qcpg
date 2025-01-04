#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_controlled_phase = QuantumCircuit(3, 3)
for i in range(2):
    repeated_controlled_phase.cp(3.14 / 2, i, i + 1)
repeated_controlled_phase.measure([0, 1, 2], [0, 1, 2])
