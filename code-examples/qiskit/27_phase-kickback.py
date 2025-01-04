#!/usr/bin/env python3

from qiskit import QuantumCircuit

phase_kickback = QuantumCircuit(2, 2)
phase_kickback.h(0)
phase_kickback.cp(3.14 / 2, 0, 1)
phase_kickback.measure([0, 1], [0, 1])
