from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.h(-1)  # Using a negative qubit index
circuit.measure([0, 1], [0, 1])
