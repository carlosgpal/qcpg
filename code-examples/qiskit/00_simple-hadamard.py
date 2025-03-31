#!/usr/bin/env python3

from qiskit import QuantumCircuit

simple_circuit = QuantumCircuit(1, 1)
simple_circuit.h(0)
simple_circuit.measure(0, 0)
