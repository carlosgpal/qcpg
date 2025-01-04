from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.measure([1, 0], [0, 1])  # Incorrect order in measurements
