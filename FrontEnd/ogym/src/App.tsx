import React from 'react';
import './App.css';
import Button from './components/atoms/Button';
import Span from './components/atoms/Span';
import Input from './components/atoms/Input';
import Label from './components/atoms/Label';

function App() {
  return (
    <div>
      <Button text='button' backgroundColor="blue"></Button>
      <Span text='테스트' />
      <div>
        <Label text='연습' />
        <Input type="text" />
      </div>
    </div>
  );
}

export default App;
