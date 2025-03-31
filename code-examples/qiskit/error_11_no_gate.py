from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
# The circuit has no operations before the measurement
circuit.measure([0, 1], [0, 1])
