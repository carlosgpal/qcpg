# QCPG – Quantum Code Property Graph

This project, [qcpg](https://github.com/carlosgpal/qcpg), enables the analysis and extraction of metrics and patterns from quantum circuits written in QASM or Qiskit. It is based on the construction of an extended Code Property Graph (CPG) that includes quantum information, allowing advanced analyses such as entanglement, subcircuit knitting, and more.

## Features

- **Quantum metrics analysis:** Extracts information about circuit size (width, depth), density, single-qubit gates, multi-qubit gates, oracles, and measurements.  
- **Pattern detection:** Identifies patterns such as state preparation, uniform superposition and entanglement creation.  
- **Subcircuit knitting:** Detects cut zones in the circuit based on PDG dependencies, enabling the division of the circuit into meaningful subcircuits.


## Usage Guide

### 1. Build the container

Execute the following command in the folder [docker](./docker/):

```shell
  docker compose up
```

### 2. Backend

- **Build the backend**: Tested for Java 17. If you have a higher version it doesn't affect, but a lower version can give problems. If you want to build with a lower version, specify it in the [pom.xml](./qcpg/pom.xml).

  Execute the following command:

  ```shell
  mvn clean install
  ```

- If the Neo4j user and password are changed from step 1, you must change it too in [application.yml](./qcpg/src/main/resources/application.yml).

- **To run it in Visual Studio Code (The execution on the server may vary in other IDEs)**: Right-click on [QcpgApplication.java](./qcpg/src/main/java/com/qcpg/qcpg/QcpgApplication.java) -> Run Java, or use the Spring Boot Dashboard extension.

### 3. Frontend

- **Build**: Tested for node v18.18.2.

  ```shell
  npm install
  ```

- **Execute**

  ```shell
  npm start
  ```

### 4. Reports