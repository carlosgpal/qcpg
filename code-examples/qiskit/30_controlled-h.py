#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_h = QuantumCircuit(2, 2)
controlled_h.ch(0, 1)
controlled_h.measure([0, 1], [0, 1])
