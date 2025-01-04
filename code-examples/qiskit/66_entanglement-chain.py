#!/usr/bin/env python3

from qiskit import QuantumCircuit

entanglement_chain = QuantumCircuit(4, 4)
entanglement_chain.h(0)
entanglement_chain.cx(0, 1)
entanglement_chain.cx(1, 2)
entanglement_chain.cx(2, 3)
entanglement_chain.measure([0, 1, 2, 3], [0, 1, 2, 3])
