# QCPG Reports

This README contains the scripts and queries needed to generate the **CSV files** required by the [QCPG Tableau Public dashboard](https://public.tableau.com/app/profile/carlos.guti.rrez.palmeiro/viz/DashboardQCPG/Resumen). These CSVs capture essential quantum circuit metrics extracted from this custom software and prepared for visualization.

---

## Output: Required CSV Files

The software must generate the following 4 CSV files, one per dataset:

| File Name              | Description                                |
|------------------------|--------------------------------------------|
| `basic-patterns.csv`   | One row per file – circuit metadata        |
| `entanglement.csv`     | One row per entangled qubit pair           |
| `knitting-circuits.csv`| One row per subcircuit                     |
| `metrics.csv`          | One row per circuit with numerical metrics |

These files are used as **data sources** in Tableau Public.

---

## 1. `basic-patterns.csv` – One Row per File

Contains unique metadata per quantum circuit.

**SQL (BigQuery) from patterns.json:**
```sql
SELECT DISTINCT
  file,
  origin,
  statePrep,
  uniform
FROM
  `<set>.<dataset>.patterns`
```

- `file`: Name or ID of the circuit file  
- `origin`: Source library or framework (e.g., qasm, qiskit)  
- `statePrep`: Boolean, whether state preparation is used  
- `uniform`: Boolean, whether superposition is uniform  

---

## 2. `entanglement.csv` – One Row per Entangled Qubit Pair

Captures every connection (entanglement) between two qubits.

**SQL (BigQuery) from patterns.json:**
```sql
SELECT
  file,
  origin,
  SAFE_CAST(REGEXP_EXTRACT(creatingEntanglement, r'Ent#(\d+)') AS INT64) AS num_entanglement,
  REGEXP_EXTRACT(creatingEntanglement, r':\s*([^,]+)') AS qubit1,
  REGEXP_EXTRACT(creatingEntanglement, r',([^,]+)$') AS qubit2
FROM
  `<set>.<dataset>.patterns`,
  UNNEST(creatingEntanglement) AS creatingEntanglement
WHERE creatingEntanglement IS NOT NULL
```

---

## 3. `knitting-circuits.csv` – One Row per Subcircuit

Captures logical structure and decomposition of each circuit.

**SQL (BigQuery) from patterns.json:**
```sql
SELECT
  file,
  origin,
  SAFE_CAST(REGEXP_EXTRACT(knittingSubcircuits, r'Subcircuit\s+(\d+):') AS INT64) AS num_subcircuit,
  REGEXP_EXTRACT(knittingSubcircuits, r'Subcircuit\s+\d+:\s*(.+)') AS subcircuit
FROM
  `<set>.<dataset>.patterns`,
  UNNEST(knittingSubcircuits) AS knittingSubcircuits
WHERE knittingSubcircuits IS NOT NULL
```

---

## 4. `metrics.json / metrics.csv` – One Row per Circuit with Metrics

This is the main dataset for analytics.

> This file is **not extracted via SQL**, but must be generated directly by this project software.

---

## Export to CSV

After running the queries above in **BigQuery**, export each table as:

- CSV file  
- UTF-8 encoding  
- With headers included  
- Decimal separator: dot (`.`)

---

## How to Use with Tableau Public

Once all 4 CSV files are generated:

1. **Open the template dashboard** in Tableau Public Desktop
2. Replace each data source:
   - Go to **Data > Replace Data Source**
   - Match your new CSV with the original data source
3. Make sure column names match exactly  
4. Save or publish your customized dashboard

