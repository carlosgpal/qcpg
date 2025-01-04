#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_sqrtx = QuantumCircuit(2, 2)
controlled_sqrtx.unitary([[1, 0], [0, (1 + 1j) / 2]], [1], label='√X').control()
controlled_sqrtx.measure([0, 1], [0, 1])
