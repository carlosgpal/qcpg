#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_swap = QuantumCircuit(3, 3)
controlled_swap.cswap(0, 1, 2)
controlled_swap.measure([0, 1, 2], [0, 1, 2])
