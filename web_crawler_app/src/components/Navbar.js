import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => (
  <nav style={{ background: '#282c34', padding: '1rem' }}>
    <Link to="/" style={{ color: '#fff', marginRight: '1rem' }}>Home</Link>
    <Link to="/logs" style={{ color: '#fff' }}>Logs</Link>
  </nav>
);

export default Navbar;
