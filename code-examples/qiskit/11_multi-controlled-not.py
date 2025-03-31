#!/usr/bin/env python3

from qiskit import QuantumCircuit

multi_controlled_not = QuantumCircuit(3, 3)
multi_controlled_not.h(0)
multi_controlled_not.h(1)
multi_controlled_not.ccx(0, 1, 2)
multi_controlled_not.measure([0, 1, 2], [0, 1, 2])
