from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.measure(0, 0)
circuit.measure(0, 0)  # Duplicating measurement on the same classical bit
