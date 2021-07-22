import { Divider } from "antd"
import { useState } from "react";
import styled from "styled-components";
import Button from "../../atoms/Button";
import Input from "../../atoms/Input"
import Label from "../../atoms/Label";
import ButtonList from "../../molecules/ButtonList";
import ListItem from "../../molecules/ListItem"
import PopupButton from "../../molecules/postcode/PopupButton";


const LabelDiv = styled.div`
    text-align: center;
`;

const RegisterContent = (): JSX.Element => {
    return (
        <>
            <LabelDiv>
                <Label label="정보를 입력해 주세요."
                    color="gray"
                    backgroundcolor=""
                />
            
            </LabelDiv>
            <hr />

            <ListItem flexdirection="column">
                <Label label="이메일" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="registerEmail" 
                  placeholder="email"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>

            <ListItem flexdirection="column">
                <Label label="비밀번호" fontweight="bold"/>
                <Input 
                  type="password" 
                  inputType="registerPassword" 
                  placeholder="비밀번호"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none" }}
                />
                <Input 
                  type="password" 
                  inputType="registerPassWordConfirmation" 
                  placeholder="비밀번호 재입력"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>

            <ListItem flexdirection="column">
                <Label label="이름" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="registerName" 
                  placeholder="이름을 입력해 주세요."
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem"}}
                />
            </ListItem>

            <ListItem flexdirection="column">
                <Label label="닉네임" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="registerNickname" 
                  placeholder="닉네임을 입력해 주세요."
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>

            <ListItem flexdirection="column">
                <Label label="전화번호" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="registerPhone" 
                  placeholder="전화번호"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>
            
            <div style={{display: "flex"}}>

              <PopupButton />
              <ListItem flexdirection="column">
                  <Input 
                    type="text" 
                    inputType="registerZipcode" 
                    placeholder="우편번호"
                    style={{ flex: 1,
                      fontSize:"16px",
                      padding:"10px 10px",
                      borderRadius:"8px",
                      border:"1px solid #BDBDBD",
                      outline:"none",
                      marginBottom: "1rem" }}
                      />
              </ListItem>
            </div>

            <ListItem flexdirection="column">
                <Input 
                  type="text" 
                  inputType="registerStreetAddress" 
                  placeholder="도로명주소"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>

            <ListItem flexdirection="column">
                <Input 
                  type="text" 
                  inputType="registerDetailedAddress" 
                  placeholder="상세주소"
                  style={{ flex: 1,
                    fontSize:"16px",
                    padding:"10px 10px",
                    borderRadius:"8px",
                    border:"1px solid #BDBDBD",
                    outline:"none",
                    marginBottom: "1rem" }}
                />
            </ListItem>
            

            <Label label="성별" fontweight="bold"/>
            <ListItem flexdirection="row">
              <Label label="남성" fontweight="bold"></Label>
              <button><Input type="radio" value={0} inputType="registerGender" inputName="gender" style={{flex: 1, textAlign: "center"}}/></button>
              <Label label="여성" fontweight="bold"></Label>
              <button><Input type="radio" value={1} inputType="registerGender" inputName="gender" style={{flex: 1, textAlign: "center"}}/></button>
            </ListItem>

            <hr />
            <ButtonList>
                <Button text="닫기" width="100%"></Button>
                <Button text="회원가입" width="100%"></Button>
            </ButtonList>
            <Divider />
        </>
    );
}

export default RegisterContent;