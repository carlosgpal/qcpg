#!/usr/bin/env python3

from qiskit import QuantumCircuit

repeated_phase = QuantumCircuit(1, 1)
for _ in range(5):
    repeated_phase.p(3.14 / 4, 0)
repeated_phase.measure(0, 0)
