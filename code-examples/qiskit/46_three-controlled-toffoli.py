#!/usr/bin/env python3

from qiskit import QuantumCircuit

three_controlled_toffoli = QuantumCircuit(4, 4)
three_controlled_toffoli.ccx(0, 1, 2)
three_controlled_toffoli.ccx(1, 2, 3)
three_controlled_toffoli.measure([0, 1, 2, 3], [0, 1, 2, 3])
