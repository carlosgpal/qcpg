#!/usr/bin/env python3

from qiskit import QuantumCircuit

swap_circuit = QuantumCircuit(2, 2)
swap_circuit.swap(0, 1)
swap_circuit.measure([0, 1], [0, 1])
