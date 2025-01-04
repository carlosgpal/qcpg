#!/usr/bin/env python3

from qiskit import QuantumCircuit

def composite_gate():
    gate = QuantumCircuit(2)
    gate.h(0)
    gate.cx(0, 1)
    gate.p(3.14 / 2, 0)
    return gate.to_gate(label="Composite Gate")

circuit = QuantumCircuit(2, 2)
circuit.append(composite_gate(), [0, 1])
circuit.measure([0, 1], [0, 1])
