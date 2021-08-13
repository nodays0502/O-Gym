import React from 'react'
import "./profile.css"
import crossFit from '../../assets/pages/mainPage/carousel/crossfit.jpg';
import ProfileRight from '../../components/organisms/ProfileRight/ProfileRight';
import ProfileDetail from '../../components/organisms/ProfileDetail/ProfileDetail';
import { useEffect, useState } from 'react';
import axios from 'axios'

function Profile() {
  const [username, setUsername] = useState('');

  // useEffect(() => {
  //   axios.get("https://i5b305.p.ssafy.io/api/user/username", {
  //     headers: {
  //       "Authorization": `Bearer ${accessToken}`
  //     }
  //   })
  //   .then((response) => console.log(response))
  // })
  return (
    <>
      <div className="profile">
        <div className="profileRight">
          <div className="profileRightTop">
            <div className="profileCover">
            <img className="profileCoverImg" src={crossFit} alt="" />
            <img className="profileUserImg" src="https://imgtag.co.kr/images/210729/210729_125520/3EYZwp.jpg" alt="" />
            </div>
            <div className="profileInfo">
              <h4 className="profileInfoName">킹호정</h4>
              <span className="profileInfoDesc">My name is KHJ</span>
            </div>
          </div>
          <div className="profileRightBottom">
            <div style={{ flex: 5.5 }}>
              <ProfileDetail />
              
            </div>
            <div style={{ flex: 3.5 }}>
              <ProfileRight />
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default Profile
