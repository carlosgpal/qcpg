#!/usr/bin/env python3

from qiskit import QuantumCircuit

double_cnot = QuantumCircuit(3, 3)
double_cnot.cx(0, 1)
double_cnot.cx(1, 2)
double_cnot.measure([0, 1, 2], [0, 1, 2])
