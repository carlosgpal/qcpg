#!/usr/bin/env python3

from qiskit import QuantumCircuit

reversed_entanglement = QuantumCircuit(4, 4)
reversed_entanglement.cx(3, 2)
reversed_entanglement.cx(2, 1)
reversed_entanglement.cx(1, 0)
reversed_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])
