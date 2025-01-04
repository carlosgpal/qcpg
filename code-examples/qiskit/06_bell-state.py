#!/usr/bin/env python3

from qiskit import QuantumCircuit

bell_state = QuantumCircuit(2, 2)
bell_state.h(0)
bell_state.cx(0, 1)
bell_state.measure([0, 1], [0, 1])
