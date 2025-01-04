#!/usr/bin/env python3

from qiskit import QuantumCircuit
import random

random_circuit = QuantumCircuit(3, 3)
for qubit in range(3):
    random_angle = random.uniform(0, 3.14)
    random_circuit.p(random_angle, qubit)
    random_circuit.rx(random_angle, qubit)
random_circuit.measure([0, 1, 2], [0, 1, 2])
