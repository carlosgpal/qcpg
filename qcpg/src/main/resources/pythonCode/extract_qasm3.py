#!/usr/bin/env python3

import sys
import importlib.util
from qiskit import QuantumCircuit
from qiskit.qasm3 import dumps  # Ensure you have this imported for OpenQASM 3 export

def main(py_file_path):
    """
    Main function to extract QuantumCircuits from a user-provided Python file and convert them to OpenQASM 3.
    
    Args:
        py_file_path (str): The path to the Python file containing Qiskit circuits.
    """
    # Dynamically load the user-provided Python file as a module
    spec = importlib.util.spec_from_file_location("user_module", py_file_path)
    user_module = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(user_module)

    # Identify all QuantumCircuit instances in the user's module
    found_circuits = []
    for var_name in dir(user_module):  # Iterate over all variables in the module
        obj = getattr(user_module, var_name)
        if isinstance(obj, QuantumCircuit):  # Check if the variable is a QuantumCircuit
            found_circuits.append(var_name)

    # Export each QuantumCircuit to OpenQASM 3 and print it
    for var_name in found_circuits:
        qc = getattr(user_module, var_name)
        qasm_str = dumps(qc)  # Convert the QuantumCircuit to OpenQASM 3 format
        print("###QASM###")  # Delimiter to separate multiple circuits
        print(qasm_str)      # Print the OpenQASM 3 string

if __name__ == "__main__":
    # Check that the script is executed with the correct number of arguments
    if len(sys.argv) < 2:
        print("Usage: python extract_qasm3.py <path_to_user_py>")
        sys.exit(1)  # Exit with an error code if no file path is provided
    py_file_path = sys.argv[1]  # Get the path to the Python file from command-line arguments
    main(py_file_path)          # Call the main function
