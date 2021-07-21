import styled from 'styled-components';

export interface ButtonPropsType {
    backgroundColor?: string;
    onclick?: Function;
}

const StyledButton = styled.button<ButtonPropsType>`
    background: ${props => props.backgroundColor || "white"}
`;

const Button = (props: any): JSX.Element => {
    
    return (
        <>
            <StyledButton backgroundColor={props.backgroundColor}
                onClick={props.onclick}
            >{props.text}</StyledButton>
        </>
    )

}

export default Button;
