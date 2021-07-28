import React from 'react'
import WeightFlow from '../../components/organisms/StudentChart/WeightFlow'
import BMIChart from '../../components/organisms/StudentChart/BMIChart'
import styled from 'styled-components';
const Fade = require('react-reveal/Fade');

const StyledDiv = styled.div`
  height: 600px;
  width: 70%;
  margin: auto;
`;

function StudentPage() {
  return (
    <>
    <h1 style={{textAlign: "center"}}>학생 건강 차트 페이지</h1>
      <Fade bottom>
        <StyledDiv>
          <WeightFlow />
        </StyledDiv>
        {/* <StyledDiv>
          <BMIChart />
        </StyledDiv> */}
      </Fade>
    </>
  )
}

export default StudentPage
