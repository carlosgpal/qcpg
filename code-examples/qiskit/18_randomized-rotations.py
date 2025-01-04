#!/usr/bin/env python3

from qiskit import QuantumCircuit
import random

random_rotations = QuantumCircuit(1, 1)
for _ in range(5):
    random_angle = random.uniform(0, 2 * 3.14159)
    random_rotations.rx(random_angle, 0)
random_rotations.measure(0, 0)
