#!/usr/bin/env python3

from qiskit import QuantumCircuit

w_state = QuantumCircuit(3, 3)
w_state.ry(1.9106, 0)
w_state.cx(0, 1)
w_state.ry(1.2309, 1)
w_state.cx(1, 2)
w_state.measure([0, 1, 2], [0, 1, 2])
