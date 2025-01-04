#!/usr/bin/env python3

from qiskit import QuantumCircuit

controlled_phase = QuantumCircuit(2, 2)
controlled_phase.cp(3.14 / 2, 0, 1)
controlled_phase.measure([0, 1], [0, 1])

reset_circuit = QuantumCircuit(1, 1)
reset_circuit.reset(0)
reset_circuit.measure(0, 0)
