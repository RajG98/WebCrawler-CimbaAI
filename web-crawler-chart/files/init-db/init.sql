CREATE TABLE IF NOT EXISTS crawl_logs (
  id SERIAL PRIMARY KEY,            
  request_payload TEXT NOT NULL,      
  response_data TEXT,                
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  
);
