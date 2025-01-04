#!/usr/bin/env python3

from qiskit import QuantumCircuit

rotation_circuit = QuantumCircuit(1, 1)
rotation_circuit.rx(3.14 / 2, 0)
rotation_circuit.ry(3.14 / 3, 0)
rotation_circuit.rz(3.14 / 4, 0)
rotation_circuit.measure(0, 0)

phase_circuit = QuantumCircuit(1, 1)
phase_circuit.p(3.14 / 2, 0)
phase_circuit.measure(0, 0)
