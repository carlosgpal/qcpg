#!/usr/bin/env python3

from qiskit import QuantumCircuit

toffoli_entanglement = QuantumCircuit(3, 3)
toffoli_entanglement.h(0)
toffoli_entanglement.cx(0, 1)
toffoli_entanglement.ccx(0, 1, 2)
toffoli_entanglement.measure([0, 1, 2], [0, 1, 2])
