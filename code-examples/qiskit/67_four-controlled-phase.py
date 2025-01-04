#!/usr/bin/env python3

from qiskit import QuantumCircuit

four_controlled_phase = QuantumCircuit(4, 4)
four_controlled_phase.cp(3.14 / 2, 0, 1)
four_controlled_phase.cp(3.14 / 4, 1, 2)
four_controlled_phase.cp(3.14 / 8, 2, 3)
four_controlled_phase.measure([0, 1, 2, 3], [0, 1, 2, 3])
