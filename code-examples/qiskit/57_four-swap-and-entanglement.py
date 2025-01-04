#!/usr/bin/env python3

from qiskit import QuantumCircuit

swap_circuit = QuantumCircuit(4, 4)
swap_circuit.swap(0, 1)
swap_circuit.swap(2, 3)
swap_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])

entanglement_circuit = QuantumCircuit(4, 4)
entanglement_circuit.h(0)
entanglement_circuit.cx(0, 1)
entanglement_circuit.cx(1, 2)
entanglement_circuit.cx(2, 3)
entanglement_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])
