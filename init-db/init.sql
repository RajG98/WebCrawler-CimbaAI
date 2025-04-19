

\c crawlerdb


CREATE TABLE crawl_logs (
  id SERIAL PRIMARY KEY,             -- Auto-incrementing unique identifier for each log entry
  request_payload TEXT NOT NULL,      -- Stores the request payload (JSON) sent in the POST request
  response_data TEXT,                -- Stores the response data (JSON or plain text) received from the API
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Timestamp for when the log is created
);

-- Insert sample data into crawl_logs
INSERT INTO crawl_logs (request_payload, response_data)
VALUES
  ('{"url": "https://example.com", "method": "GET"}', '{"status": "200", "message": "OK"}'),
  ('{"url": "https://anotherexample.com", "method": "POST", "data": {"key": "value"}}', '{"status": "500", "message": "Internal Server Error"}');
