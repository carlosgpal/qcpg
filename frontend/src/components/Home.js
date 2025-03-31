import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function Home() {
  const navigate = useNavigate();

  // Toggle state: true = QASM, false = Qiskit
  const [isQasmSelected, setIsQasmSelected] = useState(true);

  // Accumulated list of files
  const [selectedFiles, setSelectedFiles] = useState([]);

  // Indicates if we're uploading files
  const [isUploading, setIsUploading] = useState(false);

  /**
   * Switch toggle between QASM and Qiskit.
   * Also clears files upon toggling.
   */
  const handleSwitchChange = () => {
    setIsQasmSelected(!isQasmSelected);
    setSelectedFiles([]);
  };

  /**
   * Accumulate files selected by the user.
   */
  const handleFileChange = (e) => {
    const newFiles = Array.from(e.target.files);
    setSelectedFiles((prev) => [...prev, ...newFiles]);
  };

  /**
   * Remove a single file from the selected list.
   */
  const handleRemoveFile = (index) => {
    setSelectedFiles((prevFiles) => prevFiles.filter((_, i) => i !== index));
  };

  /**
   * Clear the file list entirely.
   */
  const handleClearFiles = () => {
    setSelectedFiles([]);
  };

  /**
   * Send the files to either /api/qasm/processMultiple or /api/qiskit/processMultiple,
   * with a loading spinner and error handling.
   */
  const handleAnalyze = async () => {
    if (selectedFiles.length === 0) {
      alert('Please select at least one file before analyzing.');
      return;
    }

    // Prepare FormData
    const formData = new FormData();
    selectedFiles.forEach((file) => {
      formData.append('files', file);
    });

    // Determine endpoint
    const endpoint = isQasmSelected
      ? 'http://localhost:8080/api/qasm/processMultiple'
      : 'http://localhost:8080/api/qiskit/processMultiple';

    try {
      setIsUploading(true); // show spinner

      // POST the files
      await axios.post(endpoint, formData, {
        headers: { 'Content-Type': 'multipart/form-data' },
      });

      // If successful, go to /result
      setIsUploading(false);
      navigate('/result');
    } catch (error) {
      console.error('Error uploading files:', error);
      setIsUploading(false);
      alert('An error occurred while uploading the files. Please try again.');
    }
  };

  return (
    <div className="home-container">
      <h1>Quantum Circuits Analyzer</h1>

      {/* Toggle QASM / Qiskit */}
      <div className="switch-container">
        <span>QASM</span>
        <label className="switch">
          <input
            type="checkbox"
            checked={!isQasmSelected}
            onChange={handleSwitchChange}
          />
          <span className="slider" />
        </label>
        <span>Qiskit</span>
      </div>

      {/* Button to select files */}
      <div className="file-input-container">
        <label htmlFor="fileInput" className="button-import">
          Select files
        </label>
        <input
          id="fileInput"
          type="file"
          multiple
          style={{ display: 'none' }}
          accept={isQasmSelected ? '.qasm' : '.py'}
          onChange={handleFileChange}
        />
      </div>

      {/* Show the list of selected files, each with an X button to remove */}
      <div className="files-list">
        {selectedFiles.length > 0 ? (
          <ul>
            {selectedFiles.map((file, idx) => (
              <li key={idx}>
                <button
                  className="remove-file-button"
                  onClick={() => handleRemoveFile(idx)}
                >
                  X
                </button>
                {file.name}
              </li>
            ))}
          </ul>
        ) : (
          <p>No files selected</p>
        )}
      </div>

      {/* Buttons to clear or analyze */}
      <div className="buttons-container">
        <button onClick={handleClearFiles} className="button-clear">
          Clear Files
        </button>
        <button onClick={handleAnalyze} className="button-analyze">
          Analyze
        </button>
      </div>

      {/* Overlay/spinner while uploading */}
      {isUploading && (
        <div className="loading-overlay">
          <div className="spinner" />
          <p>Uploading and processing files...</p>
        </div>
      )}
    </div>
  );
}

export default Home;
