"use client"

import { useEffect, useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom"

function Result() {
  const navigate = useNavigate()
  // Are we still waiting for the server to complete analysis + send the file?
  const [isLoading, setIsLoading] = useState(true)

  // If there's an error during the download, we store it here
  const [errorMessage, setErrorMessage] = useState("")

  useEffect(() => {
    const analyzeAndDownload = async () => {
      try {
        // Trigger the analysis
        const response = await axios.get("http://localhost:8080/api/analyze/download", {
          responseType: "blob",
        })

        // Force download
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement("a")
        link.href = url
        link.setAttribute("download", "analysis_results.zip")
        document.body.appendChild(link)
        link.click()
        link.remove()

        // Done with loading
        setIsLoading(false)
      } catch (error) {
        console.error("Error during analysis/download:", error)
        setIsLoading(false)
        setErrorMessage("An error occurred while analyzing/downloading the file. Please try again.")
      }
    }

    analyzeAndDownload()
  }, [])

  const handleGoToCombiner = () => {
    navigate("/combiner")
  }

  const handleGoToHome = () => {
    navigate("/")
  }

  return (
    <div className="result-container">
      <h2>Analysis Status</h2>

      {isLoading && !errorMessage && (
        <div className="spinner-overlay">
          <div className="spinner" />
          <p>Analyzing your files and generating report...</p>
        </div>
      )}

      {/* If there's an error, we show that message. */}
      {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>
      )}

      {/* If the process has finished loading (isLoading = false) AND there's no error, we show success */}
      {!isLoading && !errorMessage && <p>Your report has been successfully downloaded!</p>}

      <div className="buttons-container">
        <button onClick={handleGoToHome} className="button-clear">
          Back to Analysis
        </button>
        <button onClick={handleGoToCombiner} className="button-import">
          Go to Combiner
        </button>
      </div>
    </div>
  )
}

export default Result
