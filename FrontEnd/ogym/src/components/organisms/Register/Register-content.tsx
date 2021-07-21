import { Divider } from "antd"
import { useState } from "react";
import styled from "styled-components";
import Button from "../../atoms/Button";
import Input from "../../atoms/Input"
import Label from "../../atoms/Label";
import ButtonList from "../../molecules/ButtonList";
import ListItem from "../../molecules/ListItem"


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
                <Label label="아이디" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="loginEmail" 
                  placeholder="아이디를 입력" 
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
                  inputType="loginPassword" 
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
                  inputType="loginPassword" 
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
                  inputType="loginEmail" 
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
                  inputType="loginEmail" 
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
                <Label label="이메일" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="loginEmail" 
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
                <Label label="전화번호" fontweight="bold"/>
                <Input 
                  type="text" 
                  inputType="loginEmail" 
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

            <Label label="성별" fontweight="bold"/>
            <ListItem flexdirection="row">
                <Input type="radio" value="남성" inputType="loginEmail" inputName="gender"/>
                <Label label="남성" fontweight="bold"/>
                <Input type="radio" value="여성" inputType="loginEmail" inputName="gender"/>
                <Label label="여성" fontweight="bold"/>
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