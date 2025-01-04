#!/usr/bin/env python3

from qiskit import QuantumCircuit

alternating_rotations = QuantumCircuit(3, 3)
for i in range(3):
    alternating_rotations.rx(3.14 / (i + 1), i)
    alternating_rotations.rz(3.14 / (i + 2), i)
alternating_rotations.measure([0, 1, 2], [0, 1, 2])
