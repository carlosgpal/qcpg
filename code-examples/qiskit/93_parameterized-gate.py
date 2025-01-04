#!/usr/bin/env python3

from qiskit import QuantumCircuit
from qiskit.circuit import Parameter

def parameterized_gate(theta_value):
    theta = Parameter("θ")
    gate = QuantumCircuit(1)
    gate.rx(theta, 0)
    return gate.to_gate({theta: theta_value})

circuit = QuantumCircuit(1, 1)
circuit.append(parameterized_gate(3.14 / 2), [0])
circuit.measure(0, 0)
