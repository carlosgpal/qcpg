#!/usr/bin/env python3

from qiskit import QuantumCircuit

complex_entanglement = QuantumCircuit(5, 5)
complex_entanglement.h(0)
complex_entanglement.cx(0, 1)
complex_entanglement.cx(1, 2)
complex_entanglement.cx(2, 3)
complex_entanglement.cx(3, 4)
complex_entanglement.measure([0, 1, 2, 3, 4], [0, 1, 2, 3, 4])
