#!/usr/bin/env python3

from qiskit import QuantumCircuit

circuit = QuantumCircuit(3, 3)
circuit.ccx(0, 1, 2)
circuit.cz(1, 2)
circuit.measure([0, 1, 2], [0, 1, 2])
