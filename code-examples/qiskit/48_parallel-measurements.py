#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_measurements = QuantumCircuit(4, 4)
parallel_measurements.h([0, 1, 2, 3])
parallel_measurements.measure([0, 1, 2, 3], [0, 1, 2, 3])
