#!/usr/bin/env python3

from qiskit import QuantumCircuit

ghz_circuit = QuantumCircuit(5, 5)
ghz_circuit.h(0)
ghz_circuit.cx(0, 1)
ghz_circuit.cx(1, 2)
ghz_circuit.cx(2, 3)
ghz_circuit.cx(3, 4)
ghz_circuit.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])

w_state = QuantumCircuit(5, 5)
w_state.ry(1.9106, 0)
w_state.cx(0, 1)
w_state.ry(1.2309, 1)
w_state.cx(1, 2)
w_state.cx(2, 3)
w_state.cx(3, 4)
w_state.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])
