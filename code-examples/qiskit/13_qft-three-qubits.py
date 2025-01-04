#!/usr/bin/env python3

from qiskit import QuantumCircuit

qft_circuit = QuantumCircuit(3, 3)
qft_circuit.h(0)
qft_circuit.cp(3.14 / 2, 0, 1)
qft_circuit.h(1)
qft_circuit.cp(3.14 / 4, 0, 2)
qft_circuit.cp(3.14 / 2, 1, 2)
qft_circuit.h(2)
qft_circuit.measure([0, 1, 2], [0, 1, 2])
