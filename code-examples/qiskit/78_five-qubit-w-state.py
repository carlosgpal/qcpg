#!/usr/bin/env python3

from qiskit import QuantumCircuit

w_state_five = QuantumCircuit(5, 5)
w_state_five.ry(1.9106, 0)
w_state_five.cx(0, 1)
w_state_five.ry(1.2309, 1)
w_state_five.cx(1, 2)
w_state_five.cx(2, 3)
w_state_five.cx(3, 4)
w_state_five.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])
