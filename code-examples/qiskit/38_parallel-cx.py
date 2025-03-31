#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_cx = QuantumCircuit(4, 4)
parallel_cx.cx(0, 1)
parallel_cx.cx(2, 3)
parallel_cx.measure([0, 1, 2, 3], [0, 1, 2, 3])
