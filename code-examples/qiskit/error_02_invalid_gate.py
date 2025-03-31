from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.invalid_gate(0)  # Using a non-existent gate
circuit.measure([0, 1], [0, 1])
