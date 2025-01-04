#!/usr/bin/env python3

from qiskit import QuantumCircuit

reset_then_h = QuantumCircuit(1, 1)
reset_then_h.reset(0)
reset_then_h.h(0)
reset_then_h.measure(0, 0)
