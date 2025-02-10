#!/usr/bin/env python
import sys
import json
import xlsxwriter

def create_excel_dashboard(data_list, output_xlsx):
    workbook = xlsxwriter.Workbook(output_xlsx)

    title_format = workbook.add_format({
        'bold': True,
        'font_color': 'white',
        'bg_color': '#0072B5',
        'align': 'center',
        'valign': 'vcenter',
        'border': 1
    })
    header_format = workbook.add_format({
        'bold': True,
        'bg_color': '#CCCCCC',
        'border': 1
    })
    normal_format = workbook.add_format({'border': 1})
    
    summary_ws = workbook.add_worksheet("Resumen")
    
    summary_ws.write(0, 0, f"Número de circuitos analizados: {len(data_list)}", title_format)
    
    metric_keys = set()
    for item in data_list:
        for k, v in item.items():
            if k not in ["file", "patterns"] and isinstance(v, (int, float)):
                metric_keys.add(k)
    metric_keys = sorted(metric_keys)
    
    metrics_header_row = 2
    summary_ws.write(metrics_header_row, 0, "Métrica", header_format)
    summary_ws.write(metrics_header_row, 1, "Suma", header_format)
    summary_ws.write(metrics_header_row, 2, "Promedio", header_format)
    
    row = metrics_header_row + 1
    for key in metric_keys:
        total = 0
        count = 0
        for item in data_list:
            value = item.get(key)
            if isinstance(value, (int, float)):
                total += value
                count += 1
        promedio = total / count if count > 0 else 0
        summary_ws.write(row, 0, key, normal_format)
        summary_ws.write(row, 1, total, normal_format)
        summary_ws.write(row, 2, promedio, normal_format)
        row += 1
    metrics_table_end = row
    
    patterns_header_row = metrics_table_end + 1
    summary_ws.write(patterns_header_row, 0, "Patrón", header_format)
    summary_ws.write(patterns_header_row, 1, "Valores Agregados", header_format)
    
    row2 = patterns_header_row + 1

    pattern_keys = set()
    for item in data_list:
        patterns = item.get("patterns", {})
        for k in patterns.keys():
            pattern_keys.add(k)
    pattern_keys = sorted(pattern_keys)
    
    for pkey in pattern_keys:
        aggregated_values = []
        for item in data_list:
            patterns = item.get("patterns", {})
            if pkey in patterns:
                val = patterns[pkey]
                aggregated_values.append(str(val))
        aggregated_str = ", ".join(aggregated_values)
        summary_ws.write(row2, 0, pkey, normal_format)
        summary_ws.write(row2, 1, aggregated_str, normal_format)
        row2 += 1

    legend_start_row = 2
    legend_start_col = 5
    summary_ws.write(legend_start_row, legend_start_col, "Leyenda", header_format)
    legend_start_row += 1
    
    legend = {
        "width": "Número de cúbits en el circuito (nodos con QUANTUM_BIT).",
        "depth": "Número máximo de operaciones en un cúbit (máximo de relaciones a barriers, resets, measures y gates de QUANTUM_BIT).",
        "maxDens": "Número máximo de operaciones simultáneas.",
        "avgDens": "Número medio de operaciones simultáneas.",
        "noPx": "Número de puertas Pauli-X (nodos con QUANTUM_GATE_X).",
        "noPy": "Número de puertas Pauli-Y (nodos con QUANTUM_GATE_Y).",
        "noPz": "Número de puertas Pauli-Z (nodos con QUANTUM_GATE_Z).",
        "tnoP": "Número total de puertas de Pauli (nodos con QUANTUM_GATE_X, Y o Z).",
        "noH": "Número de puertas Hadamard (nodos con QUANTUM_GATE_H).",
        "psposQ": "Porcentaje de cúbits en estado de superposición inicial (calculado desde EXECUTION_ORDER, utilizado para Uniform Superposition).",
        "noOtherSG": "Número de otras puertas de un solo cúbit (excluyendo X, Y, Z, H; *¿se cuentan controladas?*).",
        "tnoSG": "Número total de puertas de un cúbit (nodos con un solo QUANTUM_OPERAND_, *¿se cuentan controladas?*).",
        "tnoCSQG": "Número total de puertas controladas de un cúbit (puede tener 2 QUANTUM_OPERAND_, *¿se cuentan controladas?*).",
        "noSWAP": "Número total de puertas SWAP (nodos QUANTUM_GATE_SWAP).",
        "noCNOT": "Número total de puertas CNOT (nodos QUANTUM_GATE_CNOT).",
        "pqinCNOT": "Porcentaje de cúbits afectados por un CNOT.",
        "avgCNOT": "Número medio de cúbits afectados por un CNOT.",
        "maxCNOT": "Número máximo de CNOT dirigidos a cualquier cúbit.",
        "noToff": "Número total de puertas Toffoli (nodos QUANTUM_GATE_CCX).",
        "pqinToff": "Porcentaje de cúbits afectados por las puertas Toffoli.",
        "maxToff": "Número máximo de puertas Toffoli.",
        "noGates": "Número total de puertas (nodos QUANTUM_GATE).",
        "noCGates": "Número total de puertas controladas (nodos de las puertas controladas, e.g., QUANTUM_GATE_CX, CY, CZ, etc.).",
        "psgates": "Porcentaje de puertas de un cúbit en el circuito (calculado a partir de nodos QUANTUM_GATE con un solo QUANTUM_OPERAND_).",
        "noOr": "Número de oráculos en el circuito.",
        "noCOr": "Número de oráculos controlados en el circuito.",
        "pqinOr": "Porcentaje de cúbits afectados por oráculos.",
        "pqinCOr": "Porcentaje de cúbits afectados por oráculos controlados.",
        "avgOrD": "Profundidad media de un oráculo en el circuito.",
        "maxOrD": "Profundidad máxima de un oráculo en el circuito.",
        "noM": "Número de puertas de medición (nodos QUANTUM_MEASURE).",
        "pqm": "Porcentaje de cúbits medidos en el circuito (calculado a partir de QUANTUM_BIT con MEASUREMENT_SOURCE).",
        "statePrep": "Patrón de preparación de estado. Si es false, se muestra 'false'; si es true, se muestra el valor correspondiente.",
        "uniform": "Indica si se usó superposición uniforme (true o false).",
        "entanglement": "Lista de pares de qubits involucrados en la creación de entrelazamiento."
    }
    
    for key, desc in legend.items():
        summary_ws.write(legend_start_row, legend_start_col, key, normal_format)
        summary_ws.write(legend_start_row, legend_start_col + 1, desc, normal_format)
        legend_start_row += 1

    aux = 0
    for item in data_list:
        base_name = item.get('file', 'Unknown')
        aux_str = f"_{aux}"
        max_base_length = 31 - len(aux_str)
        sheet_name = (base_name[:max_base_length] + aux_str) if max_base_length > 0 else f"Sheet{aux}"
        worksheet = workbook.add_worksheet(sheet_name)
        aux += 1

        worksheet.merge_range(0, 0, 0, 8, f"{item.get('file', '???')}", title_format)

        row_labels = 2
        row_values = 3
        col = 0
        all_keys = []
        for k, v in item.items():
            if k not in ["file", "patterns"] and isinstance(v, (int, float)):
                all_keys.append(k)
        all_keys.sort()
        for k in all_keys:
            worksheet.write(row_labels, col, k, header_format)
            worksheet.write(row_values, col, item.get(k, "null"), normal_format)
            col += 1
        
        pat = item.get("patterns", {})
        worksheet.write(5, 0, "Patterns", header_format)
        row_p = 6
        for pk, pv in pat.items():
            worksheet.write(row_p, 0, pk, header_format)
            if isinstance(pv, list):
                val_str = ", ".join(str(x) for x in pv)
            else:
                val_str = str(pv)
                if pk == "statePrep" and isinstance(pv, str) and pv.startswith("false->"):
                    val_str = "false"
            worksheet.write(row_p, 1, val_str, normal_format)
            row_p += 1

    workbook.close()

def main():
    if len(sys.argv) < 3:
        sys.exit("Usage: create_excel_dashboard.py <json_file> <output_xlsx>")
    json_file = sys.argv[1]
    output_xlsx = sys.argv[2]
    with open(json_file, 'r', encoding='utf-8') as f:
        data_list = json.load(f)
    create_excel_dashboard(data_list, output_xlsx)
    print("Excel dashboard created:", output_xlsx)

if __name__ == "__main__":
    main()
