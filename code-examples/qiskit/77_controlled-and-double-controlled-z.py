#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_z = QuantumCircuit(2, 2)
controlled_z.cz(0, 1)
controlled_z.measure([0, 1], [0, 1])

double_controlled_z = QuantumCircuit(3, 3)
double_controlled_z.h(2)
double_controlled_z.ccz(0, 1, 2)
double_controlled_z.h(2)
double_controlled_z.measure([0, 1, 2], [0, 1, 2])
