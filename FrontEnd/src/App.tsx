import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

axios.defaults.headers.common = {
  Pragma: 'no-cache'
};

function App() {

  const [data, setData] = useState('');
  
  useEffect(() => {
    async function fetchData() {
      const result = await axios.get(`/`);
      setData(result.data);
    }
    fetchData();
  }, [])

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        BackEnd data : {data} Learn React
      </header>
    </div>
  );
}

export default App;
