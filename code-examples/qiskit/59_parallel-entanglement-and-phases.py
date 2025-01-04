#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_entanglement = QuantumCircuit(4, 4)
parallel_entanglement.cx(0, 1)
parallel_entanglement.cx(2, 3)
parallel_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])

phase_shifts = QuantumCircuit(4, 4)
phase_shifts.p(3.14 / 2, [0, 1, 2, 3])
phase_shifts.measure([0, 1, 2, 3], [0, 1, 2, 3])
