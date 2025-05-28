"use client"

import { useState } from "react"
import { useNavigate } from "react-router-dom"

const Combiner = () => {
  const navigate = useNavigate()
  const [file1, setFile1] = useState(null)
  const [file2, setFile2] = useState(null)
  const [error, setError] = useState("")

  const handleFile1Change = (event) => {
    if (event.target.files && event.target.files[0]) {
      setFile1(event.target.files[0])
      setError("")
    }
  }

  const handleFile2Change = (event) => {
    if (event.target.files && event.target.files[0]) {
      setFile2(event.target.files[0])
      setError("")
    }
  }

  const handleCombine = () => {
    if (!file1 || !file2) {
      setError("Please upload both files.")
      return
    }

    if (file1.type !== file2.type) {
      setError("Files must be of the same type (CSV, JSON, or XML).")
      return
    }

    const fileType = file1.type
    const reader1 = new FileReader()
    const reader2 = new FileReader()

    reader1.onload = (e) => {
      const content1 = e.target.result
      reader2.onload = (e) => {
        const content2 = e.target.result
        try {
          let combined = ""
          let fileExtension = ""

          if (fileType === "text/csv") {
            const lines1 = content1.split("\n").filter((line) => line.trim() !== "")
            const lines2 = content2.split("\n").filter((line) => line.trim() !== "")
            combined = lines1[0] + "\n" + lines1.slice(1).join("\n") + "\n" + lines2.slice(1).join("\n")
            fileExtension = "csv"
          } else if (fileType === "application/json") {
            const json1 = JSON.parse(content1)
            const json2 = JSON.parse(content2)
            if (Array.isArray(json1) && Array.isArray(json2)) {
              combined = JSON.stringify([...json1, ...json2], null, 2)
            } else if (typeof json1 === "object" && typeof json2 === "object" && json1 !== null && json2 !== null) {
              combined = JSON.stringify({ ...json1, ...json2 }, null, 2)
            } else {
              setError("Unsupported JSON structure for combination.")
              return
            }
            fileExtension = "json"
          } else if (fileType === "text/xml") {
            combined = content1.trim() + "\n" + content2.trim()
            fileExtension = "xml"
          } else {
            setError("Unsupported file type.")
            return
          }

          // Create a Blob from the combined content
          const blob = new Blob([combined], { type: fileType })

          // Create a link element to trigger the download
          const link = document.createElement("a")
          link.href = URL.createObjectURL(blob)
          link.download = `combined.${fileExtension}`
          document.body.appendChild(link)
          link.click()
          document.body.removeChild(link) // Clean up the temporary link

          setError("") // Clear any previous errors
        } catch (err) {
          setError("Error combining files. Make sure they are valid " + fileType.split("/")[1].toUpperCase() + ".")
          console.error(err)
        }
      }
      reader2.readAsText(file2)
    }
    reader1.readAsText(file1)
  }

  const handleGoToAnalysis = () => {
    navigate("/")
  }

  return (
    <div className="home-container">
      <h1>File Combiner</h1>
      <div className="file-input-container">
        <label htmlFor="file1" className="button-import">
          Upload File 1 (CSV, JSON, XML)
        </label>
        <input
          id="file1"
          type="file"
          accept=".csv,.json,.xml"
          onChange={handleFile1Change}
          style={{ display: "none" }}
        />
        {file1 && <p className="selected-file">{file1.name}</p>}
      </div>

      <div className="file-input-container">
        <label htmlFor="file2" className="button-import">
          Upload File 2 (CSV, JSON, XML)
        </label>
        <input
          id="file2"
          type="file"
          accept=".csv,.json,.xml"
          onChange={handleFile2Change}
          style={{ display: "none" }}
        />
        {file2 && <p className="selected-file">{file2.name}</p>}
      </div>

      {error && <div className="error-message">{error}</div>}

      <div className="buttons-container">
        <button onClick={handleGoToAnalysis} className="button-clear">
          Go to Analysis
        </button>
        <button onClick={handleCombine} className="button-analyze" disabled={!file1 || !file2}>
          Combine Files
        </button>
      </div>
    </div>
  )
}

export default Combiner
