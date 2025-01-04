# Missing the required import for QuantumCircuit
qc = QuantumCircuit(2, 2)
qc.h(0)
qc.measure([0, 1], [0, 1])
