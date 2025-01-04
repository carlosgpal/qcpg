#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_x = QuantumCircuit(2, 2)
controlled_x.cx(0, 1)
controlled_x.measure([0, 1], [0, 1])

controlled_z = QuantumCircuit(2, 2)
controlled_z.h(0)
controlled_z.cz(0, 1)
controlled_z.h(0)
controlled_z.measure([0, 1], [0, 1])
