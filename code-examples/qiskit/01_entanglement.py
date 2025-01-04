#!/usr/bin/env python3

from qiskit import QuantumCircuit

entanglement_circuit = QuantumCircuit(2, 2)
entanglement_circuit.h(0)
entanglement_circuit.cx(0, 1)
entanglement_circuit.measure([0, 1], [0, 1])
