import React from 'react'
import {Row, Col, Rate, List, Divider } from 'antd'

const data = [
  '경력 3년',
  '미스터코리아 수상',
  'ㅇㅇ 체대 석사 졸업',
  'NSCA - CPT 취득',
  '스포츠 지도사 1급, 2급',
];

function ProfileDetail() {
  return (
    <>
      <div className="containerProfile">
          <Row>
            <Col span={6}>
              <img src="https://imgtag.co.kr/images/210729/210729_125520/3EYZwp.jpg" alt=""  width="150px"/>
              <Rate disabled defaultValue={4.5} />
              <List
                style={{width: "150px", fontSize: "0.7rem"}}
                size="small"
                dataSource={data}
                renderItem={item => <List.Item>{item}</List.Item>}
              />
            </Col>
            <Col span={18} style={{display: "flex", flexDirection: "column"}}>
              <div>
                <h3>킹호정</h3>
                <h4>피트니스 트레이너</h4>
                <p>RANKINGS: <span> 2/278 </span></p>
                <div>
                  꾸준히 하는게 가장 중요합니다.
                  저와 함께면 가능합니다.
                  야 너도 할 수 있어.
                </div>
              </div>

              <Divider />
              
              <Row>
                <Col span={8}>
                  <label>나이</label>
                </Col>
                <Col span={16}>
                  <p>비밀</p>
                </Col>
                <Col span={8}>
                  <label>이메일</label>
                </Col>
                <Col span={16}>
                  <p>abcdefg@ssafy.com</p>
                </Col>
                <Col span={8}>
                  <label>연락처</label>
                </Col>
                <Col span={16}>
                  <p>010-1234-5678</p>
                </Col>
              </Row>
            </Col>

          </Row>

      </div>
    </>
  )
}

export default ProfileDetail
