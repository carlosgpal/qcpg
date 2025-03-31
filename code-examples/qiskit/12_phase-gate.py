#!/usr/bin/env python3

from qiskit import QuantumCircuit

phase_gate = QuantumCircuit(1, 1)
phase_gate.p(1.57, 0)
phase_gate.measure(0, 0)
