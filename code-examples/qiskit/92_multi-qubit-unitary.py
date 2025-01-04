#!/usr/bin/env python3

from qiskit import QuantumCircuit

def multi_qubit_gate():
    gate = QuantumCircuit(3)
    gate.h(0)
    gate.cx(0, 1)
    gate.rz(3.14 / 4, 2)
    return gate.to_gate(label="Multi-Qubit Gate")

circuit = QuantumCircuit(3, 3)
circuit.append(multi_qubit_gate(), [0, 1, 2])
circuit.measure([0, 1, 2], [0, 1, 2])
