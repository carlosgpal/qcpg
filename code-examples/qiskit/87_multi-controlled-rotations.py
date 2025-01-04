#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_controlled_rotations = QuantumCircuit(3, 3)
multi_controlled_rotations.crx(3.14 / 3, 0, 1)
multi_controlled_rotations.cry(3.14 / 4, 1, 2)
multi_controlled_rotations.crz(3.14 / 5, 2, 0)
multi_controlled_rotations.measure([0, 1, 2], [0, 1, 2])
