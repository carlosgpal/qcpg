#!/usr/bin/env python3

from qiskit import QuantumCircuit

qft_four_qubits = QuantumCircuit(4, 4)
qft_four_qubits.h(0)
qft_four_qubits.cp(3.14 / 2, 0, 1)
qft_four_qubits.h(1)
qft_four_qubits.cp(3.14 / 4, 0, 2)
qft_four_qubits.cp(3.14 / 2, 1, 2)
qft_four_qubits.h(2)
qft_four_qubits.cp(3.14 / 8, 0, 3)
qft_four_qubits.cp(3.14 / 4, 1, 3)
qft_four_qubits.cp(3.14 / 2, 2, 3)
qft_four_qubits.h(3)
qft_four_qubits.measure([0, 1, 2, 3], [0, 1, 2, 3])
