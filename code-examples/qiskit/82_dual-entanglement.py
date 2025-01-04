#!/usr/bin/env python3

from qiskit import QuantumCircuit

dual_entanglement = QuantumCircuit(4, 4)
dual_entanglement.h(0)
dual_entanglement.cx(0, 1)
dual_entanglement.h(2)
dual_entanglement.cx(2, 3)
dual_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])
