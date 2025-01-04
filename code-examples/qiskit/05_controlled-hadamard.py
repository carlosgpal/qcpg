#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_hadamard = QuantumCircuit(2, 2)
controlled_hadamard.h(0)
controlled_hadamard.cx(0, 1)
controlled_hadamard.measure([0, 1], [0, 1])
