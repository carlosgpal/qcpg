#!/usr/bin/env python3

from qiskit import QuantumCircuit

ghz_circuit = QuantumCircuit(4, 4)
ghz_circuit.h(0)
ghz_circuit.cx(0, 1)
ghz_circuit.cx(1, 2)
ghz_circuit.cx(2, 3)
ghz_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])

separation_circuit = QuantumCircuit(4, 4)
separation_circuit.x(0)
separation_circuit.x(2)
separation_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])
