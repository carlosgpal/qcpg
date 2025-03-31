#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_ry = QuantumCircuit(2, 2)
controlled_ry.cry(1.57, 0, 1)
controlled_ry.measure([0, 1], [0, 1])
