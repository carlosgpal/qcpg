#!/usr/bin/env python3

from qiskit import QuantumCircuit

entangled_circuit = QuantumCircuit(2, 2)
entangled_circuit.h(0)
entangled_circuit.cx(0, 1)
entangled_circuit.measure([0, 1], [0, 1])

separated_circuit = QuantumCircuit(2, 2)
separated_circuit.x(0)
separated_circuit.measure([0, 1], [0, 1])
