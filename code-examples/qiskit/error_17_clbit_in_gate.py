from qiskit import QuantumCircuit
circuit = QuantumCircuit(2, 1)
circuit.h(0)
circuit.cx(0, "clbit")  # Using a classical bit as a target
circuit.measure(0, 0)
