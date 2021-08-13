import { Divider } from "antd"
import { useState } from "react";
import styled from "styled-components";
import Button from "../../atoms/Button";
import Input from "../../atoms/Input"
import Label from "../../atoms/Label";
import ButtonList from "../../molecules/ButtonList";
import ListItem from "../../molecules/ListItem"
import { LoginTokenState } from "../../../recoil/pages/LoginPageState";
import { useRecoilState, useRecoilValue } from "recoil";
import axios from "axios";
import { InputState } from "../../../recoil/atoms/InputState";


const LabelDiv = styled.div`
    text-align: center;
`;

const StyledDivider = styled(Divider)`
    height: inherit;
`;

const LoginContent = (): JSX.Element => {

    const [isShow, setIsShow] = useState(true);

    const loginInfo = useRecoilValue(InputState);
    
    const [LodalTokenState, setLodalTokenState] = useRecoilState(LoginTokenState);

    const handleShowButton = () => {
        setIsShow(!isShow);
    }

    const requestLogin = async () => {
        let id = loginInfo.loginEmail;
        let pw = loginInfo.loginPassword;
        console.log(id, pw);
        let responseData = axios.post('/api/user');
    }

    return (
        <>
            <LabelDiv>
                <Label label="Enter your email and password to sign in"
                    color="gray"
                    backgroundcolor=""
                />
            
            </LabelDiv>
            <Divider />
            
            <ListItem flexdirection="column">
                <Label label="Email" fontweight="bold"/>
                <Input type="text" inputType="loginEmail" placeholder="email"
                    
                />
            </ListItem>
            
            <Divider />
            
            <ListItem >
                <ListItem flexdirection="column">
                    <Label label="Password" fontweight="bold"/>
                    <Input type={isShow === true ? "password" : "text"} inputType="loginPassword" placeholder="password"/>
                </ListItem>
                
                <StyledDivider type="vertical" />
                <Button text={isShow === true ? "show" : "hide"} onclick={handleShowButton}></Button>
            
            </ListItem>

            <Divider />
            <ButtonList>
                <Button width="100%"
                    backgroundColor="light-gray"
                    color="red"
                    borderRadius="10px"
                >Forgot your password?</Button>
                <Button width="100%"
                    backgroundColor="green"
                    color="black"
                    borderRadius="10px"
                    onClick={requestLogin}
                >Log In</Button>
            </ButtonList>
            <Divider />
            <ButtonList>
                <Button width="100%" signInType="facebook">Sign in with Facebook</Button>
                <Button width="100%" signInType="google">Sign in with Google</Button>
                <Button width="100%" signInType="naver">Sign in with Naver</Button>
            </ButtonList>
        </>
    );
}

export default LoginContent;