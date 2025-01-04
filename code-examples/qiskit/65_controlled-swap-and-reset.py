#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_swap = QuantumCircuit(3, 3)
controlled_swap.cswap(0, 1, 2)
controlled_swap.measure([0, 1, 2], [0, 1, 2])

reset_circuit = QuantumCircuit(1, 1)
reset_circuit.reset(0)
reset_circuit.measure(0, 0)
