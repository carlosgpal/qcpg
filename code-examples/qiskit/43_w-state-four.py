#!/usr/bin/env python3

from qiskit import QuantumCircuit

w_state_four = QuantumCircuit(4, 4)
w_state_four.ry(1.9106, 0)
w_state_four.cx(0, 1)
w_state_four.ry(1.2309, 1)
w_state_four.cx(1, 2)
w_state_four.cx(2, 3)
w_state_four.measure([0, 1, 2, 3], [0, 1, 2, 3])
