import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Result from './components/Result';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/* Home screen: upload and send files to the correct endpoint */}
        <Route path="/" element={<Home />} />
        
        {/* Result screen: calls /api/analyze/download (does the analysis + returns Excel) */}
        <Route path="/result" element={<Result />} />
      </Routes>
    </Router>
  );
}

export default App;
