#!/usr/bin/env python3

from qiskit import QuantumCircuit

superposition_circuit = QuantumCircuit(2, 2)
superposition_circuit.h(0)
superposition_circuit.h(1)
superposition_circuit.measure([0, 1], [0, 1])
