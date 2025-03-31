#!/usr/bin/env python
import sys
import json
import csv
import zipfile
import os

def split_data(data_list):
    metrics_list = []
    patterns_list = []
    for item in data_list:
        metric_item = {k: v for k, v in item.items() if k != "patterns"}
        metrics_list.append(metric_item)
        pattern_item = {
            "file": item.get("file", ""),
            "statePrep": item.get("patterns", {}).get("statePrep", "None"),
            "uniform": item.get("patterns", {}).get("uniform", ""),
            "creatingEntanglement": item.get("patterns", {}).get("entanglement", []),
            "knittingSubcircuits": item.get("patterns", {}).get("knittingSubcircuits", [])
        }
        patterns_list.append(pattern_item)
    return metrics_list, patterns_list

def write_json_file(filename, data):
    with open(filename, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

def write_csv_file_metrics(filename, metrics_list):
    if not metrics_list:
        return
    keys = sorted({k for item in metrics_list for k in item.keys()})
    with open(filename, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f, delimiter=";", quotechar='"', quoting=csv.QUOTE_MINIMAL)
        writer.writerow(keys)
        for item in metrics_list:
            writer.writerow([item.get(key, "") for key in keys])

def write_csv_file_patterns(filename, patterns_list):
    header = ["file", "statePrep", "uniform", "creatingEntanglement", "knittingSubcircuits"]
    with open(filename, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f, delimiter=";", quotechar='"', quoting=csv.QUOTE_MINIMAL)
        writer.writerow(header)
        for item in patterns_list:
            ent = " | ".join(str(x) for x in item.get("creatingEntanglement", []))
            knit = " | ".join(str(x) for x in item.get("knittingSubcircuits", []))
            writer.writerow([
                item.get("file", ""),
                item.get("statePrep", "None"),
                item.get("uniform", ""),
                ent,
                knit
            ])

def dict_to_xml_str(tag, d):
    xml = f"<{tag}>"
    for key, value in d.items():
        if isinstance(value, list):
            xml += f"<{key}>"
            for item in value:
                if isinstance(item, dict):
                    xml += dict_to_xml_str("item", item)
                else:
                    xml += f"<item>{item}</item>"
            xml += f"</{key}>"
        elif isinstance(value, dict):
            xml += dict_to_xml_str(key, value)
        else:
            xml += f"<{key}>{value}</{key}>"
    xml += f"</{tag}>"
    return xml

def list_to_xml_str(root_tag, item_tag, data_list):
    xml = f"<{root_tag}>\n"
    for item in data_list:
        xml += "  " + dict_to_xml_str(item_tag, item) + "\n"
    xml += f"</{root_tag}>"
    return xml

def write_xml_file(filename, xml_str):
    with open(filename, "w", encoding="utf-8") as f:
        f.write(xml_str)

def main():
    if len(sys.argv) < 3:
        sys.exit("Usage: create_dashboard_files.py <json_file> <output_zip>")
    json_file = sys.argv[1]
    output_zip = sys.argv[2]
    
    with open(json_file, "r", encoding="utf-8") as f:
        data_list = json.load(f)
    
    metrics_list, patterns_list = split_data(data_list)
    
    metrics_json = "metrics.json"
    patterns_json = "patterns.json"
    write_json_file(metrics_json, metrics_list)
    write_json_file(patterns_json, patterns_list)
    
    metrics_csv = "metrics.csv"
    patterns_csv = "patterns.csv"
    write_csv_file_metrics(metrics_csv, metrics_list)
    write_csv_file_patterns(patterns_csv, patterns_list)
    
    metrics_xml = "metrics.xml"
    patterns_xml = "patterns.xml"
    xml_metrics = list_to_xml_str("metrics", "metric", metrics_list)
    xml_patterns = list_to_xml_str("patterns", "pattern", patterns_list)
    write_xml_file(metrics_xml, xml_metrics)
    write_xml_file(patterns_xml, xml_patterns)
    
    with zipfile.ZipFile(output_zip, "w", zipfile.ZIP_DEFLATED) as zipf:
        zipf.write(metrics_json)
        zipf.write(patterns_json)
        zipf.write(metrics_csv)
        zipf.write(patterns_csv)
        zipf.write(metrics_xml)
        zipf.write(patterns_xml)
    
    os.remove(metrics_json)
    os.remove(patterns_json)
    os.remove(metrics_csv)
    os.remove(patterns_csv)
    os.remove(metrics_xml)
    os.remove(patterns_xml)
    
    print("Dashboard files created in:", output_zip)

if __name__ == "__main__":
    main()
