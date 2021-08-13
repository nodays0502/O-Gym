import React, { StrictMode } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { RecoilRoot } from 'recoil';
import { BrowserRouter as Router } from 'react-router-dom';
import GlobalStyle from './assets/styles/global-styles';
import axios from 'axios';

axios.defaults.baseURL = "https://i5b305.p.ssafy.io";
axios.defaults.withCredentials = true;

ReactDOM.render(
  <StrictMode>
    <RecoilRoot>
      <Router>
        <GlobalStyle />
        <App />
      </Router>
    </RecoilRoot>
  </StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
