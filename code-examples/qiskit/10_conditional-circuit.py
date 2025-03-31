#!/usr/bin/env python3

from qiskit import QuantumCircuit

conditional_circuit = QuantumCircuit(2, 2)
conditional_circuit.h(0)
conditional_circuit.measure(0, 0)
conditional_circuit.x(1).c_if(0, 1)
conditional_circuit.measure(1, 1)
