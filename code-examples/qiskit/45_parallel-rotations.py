#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_rotations = QuantumCircuit(4, 4)
parallel_rotations.rx(3.14 / 3, 0)
parallel_rotations.ry(3.14 / 3, 1)
parallel_rotations.rz(3.14 / 3, 2)
parallel_rotations.rx(3.14 / 3, 3)
parallel_rotations.measure([0, 1, 2, 3], [0, 1, 2, 3])
