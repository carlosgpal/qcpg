#!/usr/bin/env python3

from qiskit import QuantumCircuit

superposition_circuit = QuantumCircuit(3, 3)
superposition_circuit.h([0, 1, 2])
superposition_circuit.measure([0, 1, 2], [0, 1, 2])

controlled_gates_circuit = QuantumCircuit(3, 3)
controlled_gates_circuit.h(0)
controlled_gates_circuit.cx(0, 1)
controlled_gates_circuit.cx(1, 2)
controlled_gates_circuit.measure([0, 1, 2], [0, 1, 2])
