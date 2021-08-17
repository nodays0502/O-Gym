import React from "react";
import styled from "styled-components";
import axios from "axios";
import { useEffect } from "react";

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

function ReservationCancel(props) {
  const myReservationList = props.reservationList

  return (
    <>
      <StyledDiv style={{overflowY: "auto"}}>
      {myReservationList.map((res:any, idx:any) => (
        <div>
          <div>
            {
              res.reservationTime.substring(0, 4) + "년 " +
              res.reservationTime.substring(5, 7) + "월 " +
              res.reservationTime.substring(8, 10) + "일 " +
              res.reservationTime.substring(11, 13) + "시"
            }
          </div>
          <div style={{display: "flex"}}>
          <div>{res.nickname}</div>
          <button onClick={() => {
            console.log(res.email, res.reservationTime)
              axios({
                method: 'delete',
                url: 'https://i5b305.p.ssafy.io/api/pt/reservation',
                data: {
                  "ptTeacherEmail" : res.email,
                  "reservationTime" : res.reservationTime,
                  "description": "예약취소"
                },
                headers: {
                  "Authorization": `Bearer ${accessToken}`
                }
              })
          }}>예약취소</button>
          </div>
        </div>
      ))}
        
      </StyledDiv>
    </>
  );
}

export default ReservationCancel;
