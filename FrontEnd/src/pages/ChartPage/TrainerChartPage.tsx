import React, {useState, useEffect} from 'react';
import ReactDOM from 'react-dom';
import 'antd/dist/antd.css';
import { Layout } from 'antd';
import StudentList from '../../components/organisms/TrainerChart/StudentList';
import axios from 'axios';
import { useRecoilState } from 'recoil'
import { Weight } from '../../recoil/atoms/Weight';


import { Swiper, SwiperSlide } from "swiper/react";
// Import Swiper styles
import "swiper/components/effect-fade/effect-fade.min.css"
import "swiper/swiper.min.css";
import "swiper/components/pagination/pagination.min.css"

import "./styles.css";
import man from "../../assets/images/man.jpg"

import SwiperCore, {
  EffectFade, Mousewheel,Pagination
} from 'swiper/core';
import styled from "styled-components";
import WeightFlowApex2 from "../../components/organisms/StudentChart/WeightFlowApex2";
import Obesity2 from "../../components/organisms/StudentChart/Obesity2";
import Bullet2 from "../../components/organisms/StudentChart/Bullet2";
import { StudentIndex } from '../../recoil/atoms/StudentIndex';
import { SelectedInfo } from '../../recoil/atoms/SelectedInfo';

// install Swiper modules
SwiperCore.use([EffectFade, Mousewheel,Pagination]);

const ImgDiv = styled.div`
  position: relative;
  background-image: url(${man});
  height: 100%;
  width: 100%;
  background-size: cover;
`

const ImgContent = styled.div`
  position: absolute;
  top:50%;
  left:50%;
  transform: translate(-50%, -50%);                                                                   
  font-size:4rem;
  color: white;
  z-index: 2;
  text-align: center;
`;

const ImgCover = styled.div`
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.4);                                                                 
  z-index:1;
`;

const { Sider, Content } = Layout;
function TrainerChartPage() {
  const [series, setSeries] = useRecoilState(Weight);
  const [studentIndex, setStudentIndex ] = useRecoilState(StudentIndex)
  const [myStudent, setMyStudent] = useState<any>([])
  const [seletedStudent, setSelectedStudent] = useState<any>({})
  const [selectedUser, setSelectedUser] = useRecoilState(SelectedInfo)


  const accessToken = 'eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRyYWluZXIxQGdvb2dsZS5jb20iLCJuaWNrbmFtZSI6InRyYWluZXJuaWNrbmFtZTEiLCJyb2xlIjoiUk9MRV9QVFRFQUNIRVIiLCJleHAiOjE2MjkxNDMxODV9.FIDI4b3TJdd2ERVCF3Z0UiutUrNpyozSV_Ir5v3iIq2f0VS-LfRnMRCjRpUuOFvi_iPNWbWpOYAspp2bQRbTaA'

  useEffect(() => {
    let today = new Date()
    let month = today.getMonth()
    axios.get(
      'https://i5b305.p.ssafy.io/api/health/mystudents', {
        headers: {
          "Authorization": `Bearer ${accessToken}`
        }
      }
    )
    .then((response) => {
      console.log(response.data.data.studentHealthList)
      setMyStudent(response.data.data.studentHealthList)
      console.log(studentIndex.index)
      setSelectedStudent(studentIndex.index)

  })
  }, [])

  return (
    <>
    <Layout style={{height: "100vh"}}>
      <Sider><StudentList myStudent={myStudent}/></Sider>
      <Layout style={{height: "100vh"}}>
        <Content>
          <Swiper direction={'vertical'} slidesPerView={1} spaceBetween={30} mousewheel={true} pagination={{"clickable": true}} className="mySwiper">
            <SwiperSlide>
              <ImgDiv>
              <ImgContent>
                <h1 style={{color:'white'}}>{selectedUser.username}님의 건강 차트</h1>
                {/* <h1 style={{color:'white'}}>건강 차트</h1> */}
              </ImgContent>
              <ImgCover></ImgCover>
              </ImgDiv>
            </SwiperSlide>
            <SwiperSlide>
              <Bullet2 myStudent={myStudent} />
            </SwiperSlide>
            <SwiperSlide>
              <Obesity2 />
            </SwiperSlide>
            <SwiperSlide >
            {/* style={{backgroundColor: "#343E59"}} */}
              <div style={{height: "70%", width: "70%", margin: "auto"}}>
                {/* <WeightFlow /> */}
                <WeightFlowApex2 />
              </div>
            </SwiperSlide>
          </Swiper>
        </Content>
      </Layout>
    </Layout>
    </>
  )
}

export default TrainerChartPage
