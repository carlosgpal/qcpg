#!/usr/bin/env python3

from qiskit import QuantumCircuit

parallel_entanglement = QuantumCircuit(4, 4)
parallel_entanglement.h([0, 2])
parallel_entanglement.cx(0, 1)
parallel_entanglement.cx(2, 3)
parallel_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])
