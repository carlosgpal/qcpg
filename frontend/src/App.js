import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import Home from "./components/Home"
import Result from "./components/Result"
import Combiner from "./components/Combiner"
import "./App.css"

function App() {
  return (
    <Router>
      <div className="app-container">
        <Routes>
          {/* Home screen: upload and send files to the correct endpoint */}
          <Route path="/" element={<Home />} />
          {/* Result screen: calls /api/analyze/download (does the analysis + returns zip) */}
          <Route path="/result" element={<Result />} />
          <Route path="/combiner" element={<Combiner />} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
