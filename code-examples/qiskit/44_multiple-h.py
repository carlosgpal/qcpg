#!/usr/bin/env python3

from qiskit import QuantumCircuit

multiple_h = QuantumCircuit(4, 4)
multiple_h.h([0, 1, 2, 3])
multiple_h.measure([0, 1, 2, 3], [0, 1, 2, 3])
