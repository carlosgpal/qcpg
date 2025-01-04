from qiskit import QuantumCircuit, ClassicalRegister
creg = ClassicalRegister(1)
circuit = QuantumCircuit(2)
# Classical register is not connected to the circuit
