from qiskit import QuantumCircuit
circuit = QuantumCircuit(3, 3)
circuit.cx(0, 1)
# The last qubit is neither connected nor measured
