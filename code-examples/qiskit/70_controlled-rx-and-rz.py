#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_rx = QuantumCircuit(2, 2)
controlled_rx.crx(3.14 / 2, 0, 1)
controlled_rx.measure([0, 1], [0, 1])

controlled_rz = QuantumCircuit(2, 2)
controlled_rz.crz(3.14 / 4, 0, 1)
controlled_rz.measure([0, 1], [0, 1])
