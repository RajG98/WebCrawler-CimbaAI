import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import CrawlForm from './components/CrawlForm';
import CrawlLogs from './components/CrawlHistory';

function App() {
  return (
    <Router>
      <Navbar />
      <div style={{ padding: '1rem' }}>
        <Routes>
          <Route path="/" element={<CrawlForm />} />
          <Route path="/logs" element={<CrawlLogs />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
