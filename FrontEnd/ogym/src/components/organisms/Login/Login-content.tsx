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

const StyledDivider = styled(Divider)`
    
`;

const LoginContent = (): JSX.Element => {

    const [isShow, setIsShow] = useState(true);

    const handleShowButton = () => {
        setIsShow(!isShow);
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
                <Input type="text" inputType="loginEmail" placeholder="email"/>
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
                <Button text="Forgot your password?"></Button>
                <Button text="Log In"></Button>
            </ButtonList>
            <Divider />
            <ButtonList>
                <Button text="Sign in with Facebook"></Button>
                <Button text="Sign in with Google+"></Button>
                <Button text="Sign in with Naver"></Button>
            </ButtonList>
        </>
    );
}

export default LoginContent;