from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.h(0  # Missing closing parenthesis
circuit.measure([0, 1], [0, 1])
