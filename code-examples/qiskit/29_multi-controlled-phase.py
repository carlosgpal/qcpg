#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_controlled_phase = QuantumCircuit(3, 3)
multi_controlled_phase.h(0)
multi_controlled_phase.cp(3.14 / 4, 0, 1)
multi_controlled_phase.cp(3.14 / 2, 0, 2)
multi_controlled_phase.measure([0, 1, 2], [0, 1, 2])
