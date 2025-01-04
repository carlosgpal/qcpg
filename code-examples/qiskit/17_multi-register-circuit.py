#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_register = QuantumCircuit(2, 1)
multi_register.h(0)
multi_register.cx(0, 1)
multi_register.measure(1, 0)
