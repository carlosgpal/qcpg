#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_rotations = QuantumCircuit(2, 2)
controlled_rotations.crx(3.14 / 2, 0, 1)
controlled_rotations.cry(3.14 / 3, 0, 1)
controlled_rotations.crz(3.14 / 4, 0, 1)
controlled_rotations.measure([0, 1], [0, 1])
