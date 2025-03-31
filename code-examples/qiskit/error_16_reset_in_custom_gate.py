from qiskit import QuantumCircuit

custom_gate = QuantumCircuit(1)
custom_gate.reset(0)  # Reset inside a custom gate
custom_gate.to_gate(label="Reset Gate")
