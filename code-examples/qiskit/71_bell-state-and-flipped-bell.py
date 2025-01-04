#!/usr/bin/env python3

from qiskit import QuantumCircuit

bell_state = QuantumCircuit(2, 2)
bell_state.h(0)
bell_state.cx(0, 1)
bell_state.measure([0, 1], [0, 1])

flipped_bell = QuantumCircuit(2, 2)
flipped_bell.x(0)
flipped_bell.h(0)
flipped_bell.cx(0, 1)
flipped_bell.measure([0, 1], [0, 1])
