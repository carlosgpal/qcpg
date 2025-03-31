from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.h(0)  # Does not connect the rest of the circuit with additional operations
