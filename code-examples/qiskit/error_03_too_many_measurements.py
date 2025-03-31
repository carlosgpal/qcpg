from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.measure([0, 1, 2], [0, 1])  # Too many qubits in measurement
