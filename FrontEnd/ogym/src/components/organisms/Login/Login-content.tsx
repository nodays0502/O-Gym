import { Divider } from "antd"
import styled from "styled-components";
import Button from "../../atoms/Button";
import Input from "../../atoms/Input"
import Label from "../../atoms/Label";
import ButtonList from "../../molecules/ButtonList";
import ListItem from "../../molecules/ListItem"

const StyledLabel = styled(Label)`
    background: red;
`;


const LoginContent = (): JSX.Element => {
    return (
        <>
            <StyledLabel label="Enter your email and password to sign in"/>
            
            <Divider />
            
            <ListItem>
                <Label label="Email"/>
                <Input type="text"/>
            </ListItem>
            
            <Divider />
            
            <ListItem>
                <Label label="Password"/>
                <Input type="text"/>
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