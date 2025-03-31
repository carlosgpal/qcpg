#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_rotations = QuantumCircuit(1, 1)
for _ in range(3):
    repeated_rotations.rx(3.14 / 3, 0)
repeated_rotations.measure(0, 0)
