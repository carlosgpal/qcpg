from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 2)
circuit.x(0).c_if(0, 2.5)  # Unsupported classical condition (non-integer value)
circuit.measure([0, 1], [0, 1])
