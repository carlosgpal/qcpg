#!/usr/bin/env python3

from qiskit import QuantumCircuit

grover_oracle = QuantumCircuit(3, 3)
grover_oracle.h([0, 1, 2])
grover_oracle.cz(0, 2)
grover_oracle.h([0, 1, 2])
grover_oracle.measure([0, 1, 2], [0, 1, 2])
