#!/usr/bin/env python3

from qiskit import QuantumCircuit

def custom_controlled_gate():
    gate = QuantumCircuit(2)
    gate.h(0)
    gate.cx(0, 1)
    return gate.to_gate(label="Custom Controlled").control(1)

circuit = QuantumCircuit(3, 3)
circuit.append(custom_controlled_gate(), [0, 1, 2])
circuit.measure([0, 1, 2], [0, 1, 2])
