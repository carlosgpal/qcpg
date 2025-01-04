#!/usr/bin/env python3

from qiskit import QuantumCircuit

toffoli_circuit = QuantumCircuit(4, 4)
toffoli_circuit.ccx(0, 1, 2)
toffoli_circuit.ccx(2, 3, 1)
toffoli_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])

grover_circuit = QuantumCircuit(4, 4)
grover_circuit.h([0, 1, 2, 3])
grover_circuit.cz(0, 3)
grover_circuit.h([0, 1, 2, 3])
grover_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])
