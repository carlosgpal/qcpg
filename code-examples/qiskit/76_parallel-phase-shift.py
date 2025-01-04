#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_phase_shift = QuantumCircuit(3, 3)
parallel_phase_shift.p(3.14 / 4, 0)
parallel_phase_shift.p(3.14 / 8, 1)
parallel_phase_shift.p(3.14 / 16, 2)
parallel_phase_shift.measure([0, 1, 2], [0, 1, 2])
