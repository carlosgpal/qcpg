#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_controlled_phase_shift = QuantumCircuit(4, 4)
multi_controlled_phase_shift.cp(3.14 / 8, 0, 1)
multi_controlled_phase_shift.cp(3.14 / 4, 1, 2)
multi_controlled_phase_shift.cp(3.14 / 2, 2, 3)
multi_controlled_phase_shift.measure([0, 1, 2, 3], [0, 1, 2, 3])
