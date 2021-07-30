import React from 'react'
import { useState } from 'react';
import WeightFlow from '../../components/organisms/StudentChart/WeightFlow'
import BMIChart from '../../components/organisms/StudentChart/BMIChart'
import styled from 'styled-components';
const Fade = require('react-reveal/Fade');

const StyledDiv = styled.div`
  height: 85vh;
  width: 70%;
  margin: auto;
  display: flex;
`;

function StudentPage() {


  return (
    <div style={{overflow: "scroll"}}>
    <h1 style={{textAlign: "center"}}>학생 건강 차트 페이지</h1>
      <Fade bottom >
        <StyledDiv>
          <WeightFlow />
        </StyledDiv>
      </Fade>
      <Fade bottom>
        <StyledDiv>
          <BMIChart />
        </StyledDiv>
      </Fade>
    </div>
  )
}

export default StudentPage
