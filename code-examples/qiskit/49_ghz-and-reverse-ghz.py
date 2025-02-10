#!/usr/bin/env python3

from qiskit import QuantumCircuit

ghz_circuit = QuantumCircuit(3, 3)
ghz_circuit.h(0)
ghz_circuit.cx(0, 1)
ghz_circuit.cx(1, 2)
ghz_circuit.measure([0, 1, 2], [0, 1, 2])

reverse_ghz = QuantumCircuit(3, 3)
reverse_ghz.h(2)
reverse_ghz.cx(2, 1)
reverse_ghz.cx(1, 0)
reverse_ghz.measure([0, 1, 2], [0, 1, 2])
