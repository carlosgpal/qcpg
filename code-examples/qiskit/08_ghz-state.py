#!/usr/bin/env python3

from qiskit import QuantumCircuit

ghz_state = QuantumCircuit(3, 3)
ghz_state.h(0)
ghz_state.cx(0, 1)
ghz_state.cx(1, 2)
ghz_state.measure([0, 1, 2], [0, 1, 2])
