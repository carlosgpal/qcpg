#!/usr/bin/env python3

from qiskit import QuantumCircuit

grover_circuit = QuantumCircuit(3, 3)
grover_circuit.h([0, 1, 2])
grover_circuit.cz(0, 2)
grover_circuit.h([0, 1, 2])
grover_circuit.measure([0, 1, 2], [0, 1, 2])

multi_ghz_circuit = QuantumCircuit(4, 4)
multi_ghz_circuit.h(0)
multi_ghz_circuit.cx(0, 1)
multi_ghz_circuit.cx(1, 2)
multi_ghz_circuit.cx(2, 3)
multi_ghz_circuit.measure([0, 1, 2, 3], [0, 1, 2, 3])
