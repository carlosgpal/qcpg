#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_controlled_z = QuantumCircuit(4, 4)
parallel_controlled_z.cz(0, 1)
parallel_controlled_z.cz(2, 3)
parallel_controlled_z.measure([0, 1, 2, 3], [0, 1, 2, 3])
