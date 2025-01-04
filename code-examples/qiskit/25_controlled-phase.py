#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_phase = QuantumCircuit(2, 2)
controlled_phase.h(0)
controlled_phase.cp(3.14 / 4, 0, 1)
controlled_phase.measure([0, 1], [0, 1])
