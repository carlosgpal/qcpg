#!/usr/bin/env python3

from qiskit import QuantumCircuit

def custom_entanglement_gate():
    custom_gate = QuantumCircuit(2)
    custom_gate.h(0)
    custom_gate.cx(0, 1)
    return custom_gate.to_gate(label="Custom Entanglement")

circuit = QuantumCircuit(2, 2)
circuit.append(custom_entanglement_gate(), [0, 1])
circuit.measure([0, 1], [0, 1])
