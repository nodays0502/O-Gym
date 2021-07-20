import React from 'react';
import './App.css';
import 'antd/dist/antd.css';
import Button from './components/atoms/Button';
import Span from './components/atoms/Span';
import Input from './components/atoms/Input';
import Label from './components/atoms/Label';
import ListItem from './components/molecules/ListItem';
import LoginPage from './pages/LoginPage/LoginPage';

function App() {
  return (
    <>
      {/* <Button text='button' backgroundColor="blue"></Button>
      <Span text='테스트' />
      <div>
        <Label text='연습' />
        <Input type="text" />
      </div> */}
      {/* <ListItem>
        <Input type="text" value='button' ></Input>
        <Button text='button' backgroundColor="blue"></Button>
      </ListItem> */}
      <LoginPage />
    </>
  );
}

export default App;
