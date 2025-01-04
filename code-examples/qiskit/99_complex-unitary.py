#!/usr/bin/env python3

from qiskit import QuantumCircuit

def complex_unitary_gate():
    gate = QuantumCircuit(3)
    gate.rx(3.14 / 2, 0)
    gate.ry(3.14 / 4, 1)
    gate.rz(3.14 / 8, 2)
    gate.cx(0, 1)
    gate.cx(1, 2)
    return gate.to_gate(label="Complex Unitary")

circuit = QuantumCircuit(3, 3)
circuit.append(complex_unitary_gate(), [0, 1, 2])
circuit.measure([0, 1, 2], [0, 1, 2])
