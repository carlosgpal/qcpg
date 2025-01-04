#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_controlled_swap = QuantumCircuit(3, 3)
multi_controlled_swap.cswap(0, 1, 2)
multi_controlled_swap.measure([0, 1, 2], [0, 1, 2])
