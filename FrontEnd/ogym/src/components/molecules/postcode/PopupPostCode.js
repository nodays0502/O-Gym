import React from "react";
import { useSetRecoilState } from "recoil";
import DaumPostcode from "react-daum-postcode";
import { Zipcode } from "../../../recoil/atoms/Zipcode";
import { StreetAddress } from "../../../recoil/atoms/StreetAddress";

const PopupPostCode = (props) => {
  const setZipCode = useSetRecoilState(Zipcode);
  const setStreetAddress = useSetRecoilState(StreetAddress);

  // 우편번호 검색 후 주소 클릭 시 실행될 함수, data callback 용
  const handlePostCode = (data) => {
    // let fullAddress = data.address;
    // let extraAddress = "";

    // if (data.addressType === "R") {
    //   if (data.bname !== "") {
    //     extraAddress += data.bname;
    //   }
    //   if (data.buildingName !== "") {
    //     extraAddress +=
    //       extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
    //   }
    //   fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    // }
    // console.log(data);
    // console.log(data.address);
    // console.log(data.zonecode);
    setZipCode(data.zonecode);
    setStreetAddress(data.address);
    // setText({ ...text, registerZipcode: data.zonecode });

    props.onClose();
  };

  const postCodeStyle = {
    display: "block",
    position: "absolute",
    top: "10%",
    width: "600px",
    height: "600px",
    padding: "7px",
  };

  return (
    <div>
      <DaumPostcode style={postCodeStyle} onComplete={handlePostCode} />
      {/* 닫기 버튼 생성 */}
      <button
        type="button"
        onClick={() => {
          props.onClose();
        }}
        className="postCode_btn"
      >
        닫기
      </button>
    </div>
  );
};

export default PopupPostCode;
