#!/usr/bin/env python3

from qiskit import QuantumCircuit

three_qubit_circuit = QuantumCircuit(3, 3)
three_qubit_circuit.h(0)
three_qubit_circuit.barrier()
three_qubit_circuit.barrier(0, 1)
three_qubit_circuit.reset([0, 1])
three_qubit_circuit.reset(2)
three_qubit_circuit.cx(0, 1)
three_qubit_circuit.cx(1, 2)
three_qubit_circuit.measure([0, 1, 2], [0, 1, 2])
