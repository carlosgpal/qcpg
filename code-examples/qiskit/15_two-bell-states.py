#!/usr/bin/env python3

from qiskit import QuantumCircuit

first_bell = QuantumCircuit(2, 2)
first_bell.h(0)
first_bell.cx(0, 1)
first_bell.measure([0, 1], [0, 1])

second_bell = QuantumCircuit(2, 2)
second_bell.h(0)
second_bell.cx(0, 1)
second_bell.measure([0, 1], [0, 1])
