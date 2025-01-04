#!/usr/bin/env python3

from qiskit import QuantumCircuit

def custom_rx_gate():
    gate = QuantumCircuit(1)
    gate.rx(3.14 / 2, 0)
    return gate.to_gate(label="Rx Gate")

circuit = QuantumCircuit(3, 3)
for qubit in range(3):
    circuit.append(custom_rx_gate(), [qubit])
circuit.measure([0, 1, 2], [0, 1, 2])
