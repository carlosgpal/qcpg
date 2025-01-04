#!/usr/bin/env python3

from qiskit import QuantumCircuit

four_controlled_gates = QuantumCircuit(4, 4)
four_controlled_gates.h(0)
four_controlled_gates.cx(0, 1)
four_controlled_gates.cx(1, 2)
four_controlled_gates.cx(2, 3)
four_controlled_gates.measure([0, 1, 2, 3], [0, 1, 2, 3])
