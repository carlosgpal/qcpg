#!/usr/bin/env python3

from qiskit import QuantumCircuit

def controlled_unitary_gate():
    unitary_gate = QuantumCircuit(2)
    unitary_gate.rx(3.14 / 4, 0)
    unitary_gate.ry(3.14 / 4, 1)
    return unitary_gate.to_gate(label="Unitary").control(1)

circuit = QuantumCircuit(3, 3)
circuit.append(controlled_unitary_gate(), [0, 1, 2])
circuit.measure([0, 1, 2], [0, 1, 2])
