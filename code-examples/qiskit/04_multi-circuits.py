#!/usr/bin/env python3

from qiskit import QuantumCircuit

first_circuit = QuantumCircuit(1, 1)
first_circuit.h(0)
first_circuit.measure(0, 0)

second_circuit = QuantumCircuit(2, 2)
second_circuit.x(0)
second_circuit.cx(0, 1)
second_circuit.measure([0, 1], [0, 1])
