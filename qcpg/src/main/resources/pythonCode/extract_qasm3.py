#!/usr/bin/env python3
"""
This script loads a user-provided Python file that contains Qiskit circuits,
extracts all defined QuantumCircuit objects (ignoring those with names starting
with an underscore or those whose name includes 'qc_basis' – which are ghost
duplicates produced by transpile), converts each circuit to OpenQASM 3 using
Qiskit's dumps function, and prints each circuit's QASM code separated by a
delimiter.
"""

import sys
import importlib.util
from qiskit import QuantumCircuit
from qiskit.qasm3 import dumps  # Used to convert a QuantumCircuit to an OpenQASM 3 string

def main(py_file_path):
    """
    Main function to extract QuantumCircuit instances from a given Python file,
    convert them to OpenQASM 3 format, and print each circuit's QASM code.
    
    Ghost duplicates generated by transpile (identified by having "qc_basis" in
    their variable name) are skipped.
    
    Args:
        py_file_path (str): The path to the Python file containing Qiskit circuits.
    """
    # Dynamically load the user-provided Python file as a module.
    spec = importlib.util.spec_from_file_location("user_module", py_file_path)
    user_module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(user_module)

    # Iterate over all attributes of the module and collect those that are
    # instances of QuantumCircuit.
    found_circuits = []
    for var_name, obj in vars(user_module).items():
        if isinstance(obj, QuantumCircuit):
            # Skip circuits with names containing "qc_basis" (ghost duplicates from transpile)
            if "qc_basis" in var_name:
                continue
            found_circuits.append(var_name)

    if not found_circuits:
        print("No QuantumCircuit instances found in the provided file.")
        sys.exit(1)

    # For each circuit found, convert it to QASM 3 and print the result.
    for var_name in found_circuits:
        qc = getattr(user_module, var_name)
        try:
            # Convert the QuantumCircuit to an OpenQASM 3 string.
            qasm_str = dumps(qc)
        except Exception as e:
            print(f"Error converting circuit '{var_name}': {e}")
            continue

        # Print a delimiter and the QASM code for this circuit.
        print("###QASM###")
        print(qasm_str)

if __name__ == "__main__":
    # Ensure that the user provided the path to the Python file as a command-line argument.
    if len(sys.argv) < 2:
        print("Usage: python extract_qasm3.py <path_to_user_py>")
        sys.exit(1)
    py_file_path = sys.argv[1]
    main(py_file_path)
