import React, { useState } from 'react';

const CrawlForm = () => {
  const [url, setUrl] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    fetch(`http://localhost:8081/api/crawl?url=${encodeURIComponent(url)}`, {
      method: 'POST'
    })
      .then(res => res.text())
      .then(data => {
        // Try to extract JSON substring
        const match = data.match(/\{.*\}$/); // gets the part like {"response":"..."}
        if (match) {
          const parsed = JSON.parse(match[0]);
          setMessage(parsed.response);
        } else {
          setMessage(data); // fallback if format doesn't match
        }
        console.log('Response:', data);
      })
      .catch(err => {
        console.error(err);
        setMessage('Error occurred during crawling.');
      });
  };

  return (
    <div style={{ fontFamily: 'sans-serif', padding: '1rem' }}>
      <h2>Submit URL to Crawl</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter URL"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          style={{
            padding: '0.5rem',
            width: '300px',
            border: '1px solid #ccc',
            borderRadius: '4px'
          }}
          required
        />
        <button
          type="submit"
          style={{
            marginLeft: '1rem',
            padding: '0.5rem 1rem',
            backgroundColor: '#007bff',
            color: 'white',
            border: 'none',
            borderRadius: '4px',
            cursor: 'pointer'
          }}
        >
          Crawl
        </button>
      </form>

      {message && (
        <div style={{ marginTop: '1.5rem', background: '#f9f9f9', padding: '1rem', borderRadius: '6px', border: '1px solid #ddd' }}>
          <h4>📝 Crawl Result</h4>
          <p style={{ whiteSpace: 'pre-wrap' }}>{message}</p>
        </div>
      )}
    </div>
  );
};

export default CrawlForm;
