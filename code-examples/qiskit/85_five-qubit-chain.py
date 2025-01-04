#!/usr/bin/env python3

from qiskit import QuantumCircuit

five_qubit_chain = QuantumCircuit(5, 5)
five_qubit_chain.h(0)
for i in range(4):
    five_qubit_chain.cx(i, i + 1)
five_qubit_chain.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])
