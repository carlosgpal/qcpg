#!/usr/bin/env python3

from qiskit import QuantumCircuit

toffoli_circuit = QuantumCircuit(3, 3)
toffoli_circuit.ccx(0, 1, 2)
toffoli_circuit.measure([0, 1, 2], [0, 1, 2])
