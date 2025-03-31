#!/usr/bin/env python3

from qiskit import QuantumCircuit

rotation_circuit = QuantumCircuit(1, 1)
rotation_circuit.rx(1.57, 0)
rotation_circuit.ry(3.14, 0)
rotation_circuit.rz(1.57, 0)
rotation_circuit.measure(0, 0)
