import React, { useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

function App() {

  const [data, setData] = useState('');
  
  useEffect(() => {
    async function fetchData() {
      const result = await axios.get(`http://ec2-13-124-95-248.ap-northeast-2.compute.amazonaws.com:32286/`);
      setData(result.data);
    }
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
