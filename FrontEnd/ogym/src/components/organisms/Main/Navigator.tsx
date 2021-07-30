import React, { useState} from 'react';
import { Link } from "react-router-dom";
import styled from 'styled-components';
import "./Navigator.css";
import barChart from "../../../assets/images/bar-chart.png"
import conference from "../../../assets/images/conference.png"
import onlineBooking from "../../../assets/images/online-booking.png"
import reserved from "../../../assets/images/reserved.png"
import work from "../../../assets/images/work.png"
import ogym1 from "../../../assets/images/ogym1.png"
import ogym2 from "../../../assets/images/ogym2.png"

const Navigator = () => {
  const [sidebar, setSidebar] = useState(false);
  

  const showSidebar = () => setSidebar(!sidebar);
  return (
    <>
      <img src={ogym1} alt="로고" width="100" height="100" onClick={showSidebar}/>
      <div style={{height: "100vh", display: "flex", flexDirection: "column"}} className={sidebar ? "nav-menu active" : "nav-menu"}>
        <div style={{height: "10%", display: "flex", backgroundColor: "#96D5D7"}}>
          <img src={ogym2} alt="로고" width="5%" height="100%" onClick={showSidebar}/>
        </div> 
        <div style={{height: "30%", display: "flex"}}>
          <div style={{flex: "1", backgroundColor: "#F08C8C", display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
            <img src={barChart} alt="내 건강분석" width="10%" height="35%"/>
            <p style={{color: "white", fontSize: "1.5rem", marginTop: "1rem"}}>내 건강 분석</p>
          </div>
          <div style={{flex: "1", backgroundColor: "#dcdcdc"}}>선생</div>
        </div>
        <div style={{height: "30%", display: "flex"}}>
          <div style={{flex: "1", backgroundColor: "#dcdcdc"}}>선생</div>
          <div style={{flex: "1", backgroundColor: "#96C7ED", display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
            <img src={reserved} alt="예약" width="10%" height="35%"/>
            <p style={{color: "white", fontSize: "1.5rem", marginTop: "1rem"}}>PT 예약 / 취소</p>
          </div>
        </div>
        <div style={{height: "30%", display: "flex"}}>
          <div style={{flex: "1", backgroundColor: "#91F8D0", display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column"}}>
            <img src={conference} alt="예약" width="10%" height="35%"/>
            <p style={{color: "white", fontSize: "1.5rem", marginTop: "1rem"}}>PT 화상 채팅방 접속하기</p>
          </div>
          <div style={{flex: "1", backgroundColor: "#dcdcdc"}}>선생</div>
        </div>
      </div>
    </>
  );
};

export default Navigator;