#!/usr/bin/env python3

from qiskit import QuantumCircuit

swap_network = QuantumCircuit(4, 4)
swap_network.swap(0, 1)
swap_network.swap(2, 3)
swap_network.measure([0, 1, 2, 3], [0, 1, 2, 3])
