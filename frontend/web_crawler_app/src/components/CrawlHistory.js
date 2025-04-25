import React, { useEffect, useState } from 'react';

const CrawlHistory = () => {
  const [logs, setLogs] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch('/api/logs')
      .then(res => res.json())
      .then(data => {
        setLogs(data);
        // console.log('Logs:logloglg', data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Error:', err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading logs...</p>;

  return (
    <div style={{ padding: '1rem' }}>
      <h2>Crawl Logs</h2>
      {logs.map((log, index) => (
        <div key={index} style={{ border: '1px solid #ddd', margin: '1rem 0', padding: '1rem' }}>
          <p><strong>ID:</strong> {log.id}</p>
          <p><strong>Request:</strong></p>
          <pre>URL: {JSON.parse(log.request).url}</pre>
          <p><strong>Response:</strong></p>
          <pre style={{ whiteSpace: 'pre-wrap', wordBreak: 'break-word' }}>Response: {JSON.parse(log.response).response}</pre>
        </div>
      ))}
    </div>
  );
};

export default CrawlHistory;
