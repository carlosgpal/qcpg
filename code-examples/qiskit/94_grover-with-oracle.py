#!/usr/bin/env python3

from qiskit import QuantumCircuit

def grover_oracle():
    oracle = QuantumCircuit(3)
    oracle.cz(0, 2)
    return oracle.to_gate(label="Oracle")

circuit = QuantumCircuit(3, 3)
circuit.h([0, 1, 2])
circuit.append(grover_oracle(), [0, 1, 2])
circuit.measure([0, 1, 2], [0, 1, 2])
