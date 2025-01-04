#!/usr/bin/env python3

from qiskit import QuantumCircuit

amplitude_damping = QuantumCircuit(1, 1)
amplitude_damping.reset(0)
amplitude_damping.h(0)
amplitude_damping.measure(0, 0)
