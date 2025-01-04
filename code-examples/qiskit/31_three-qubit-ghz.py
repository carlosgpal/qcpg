#!/usr/bin/env python3

from qiskit import QuantumCircuit

three_qubit_ghz = QuantumCircuit(3, 3)
three_qubit_ghz.h(0)
three_qubit_ghz.cx(0, 1)
three_qubit_ghz.cx(1, 2)
three_qubit_ghz.measure([0, 1, 2], [0, 1, 2])
