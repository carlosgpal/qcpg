from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.h("qubit")  # Using a string instead of an integer index
circuit.measure([0, 1], [0, 1])
