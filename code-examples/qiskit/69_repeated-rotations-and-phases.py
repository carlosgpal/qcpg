#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_rotations = QuantumCircuit(3, 3)
for qubit in range(3):
    repeated_rotations.rx(3.14 / 2, qubit)
    repeated_rotations.ry(3.14 / 4, qubit)
repeated_rotations.measure([0, 1, 2], [0, 1, 2])

repeated_phases = QuantumCircuit(3, 3)
for qubit in range(3):
    repeated_phases.p(3.14 / 8, qubit)
repeated_phases.measure([0, 1, 2], [0, 1, 2])
