import React from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Radio } from 'antd';

function TimeSchedule() {
  return (
    <>
      <Radio.Group defaultValue="a" buttonStyle="solid">
      <Radio.Button value="a">09:00</Radio.Button>
      <Radio.Button value="b">10:00</Radio.Button>
      <Radio.Button value="c">11:00</Radio.Button>
      <Radio.Button value="d">12:00</Radio.Button>
      <Radio.Button value="d">15:00</Radio.Button>
      <Radio.Button value="d">20:00</Radio.Button>
    </Radio.Group>
    </>
  )
}

export default TimeSchedule
