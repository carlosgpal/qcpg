import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Result() {
  // Are we still waiting for the server to complete analysis + send the file?
  const [isLoading, setIsLoading] = useState(true);

  // If there's an error during the download, we store it here
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    const analyzeAndDownload = async () => {
      try {
        // Trigger the analysis & get the Excel file
        const response = await axios.get('http://localhost:8080/api/analyze/download', {
          responseType: 'blob',
        });

        // Force download
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'analysis_results.xlsx'); 
        document.body.appendChild(link);
        link.click();
        link.remove();

        // Done with loading
        setIsLoading(false);
      } catch (error) {
        console.error('Error during analysis/download:', error);
        setIsLoading(false);
        setErrorMessage('An error occurred while analyzing/downloading the file. Please try again.');
      }
    };

    analyzeAndDownload();
  }, []);

  return (
    <div className="result-container">
      <h2>Analysis Status</h2>

      {isLoading && !errorMessage && (
        <div className="spinner-overlay">
          <div className="spinner" />
          <p>Analyzing your files and generating Excel report...</p>
        </div>
      )}

      {/* If there's an error, we show that message. */}
      {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>
      )}

      {/* If the process has finished loading (isLoading = false) AND there's no error, we show success */}
      {!isLoading && !errorMessage && (
        <p>Your Excel report has been successfully downloaded!</p>
      )}
    </div>
  );
}

export default Result;
