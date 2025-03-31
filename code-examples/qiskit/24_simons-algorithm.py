#!/usr/bin/env python3

from qiskit import QuantumCircuit

simons_circuit = QuantumCircuit(3, 3)
simons_circuit.h([0, 1])
simons_circuit.cx(0, 2)
simons_circuit.cx(1, 2)
simons_circuit.h([0, 1])
simons_circuit.measure([0, 1], [0, 1])
