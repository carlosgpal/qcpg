#!/usr/bin/env python3

from qiskit import QuantumCircuit

serial_entanglement = QuantumCircuit(4, 4)
serial_entanglement.cx(0, 1)
serial_entanglement.cx(1, 2)
serial_entanglement.cx(2, 3)
serial_entanglement.measure([0, 1, 2, 3], [0, 1, 2, 3])
