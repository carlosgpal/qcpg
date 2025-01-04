from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.h(3)  # Operating on a non-existent qubit
circuit.measure([0, 1], [0, 1])
