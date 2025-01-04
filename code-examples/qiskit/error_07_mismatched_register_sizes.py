from qiskit import QuantumCircuit
circuit = QuantumCircuit(3, 2)
circuit.measure([0, 1, 2], [0, 1])  # More qubits than classical bits
