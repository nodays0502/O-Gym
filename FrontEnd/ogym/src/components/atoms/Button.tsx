import styled from 'styled-components';

interface ButtonPropsType {
    backgroundColor?: string;
}

const StyledButton = styled.button<ButtonPropsType>`
    background: ${props => props.backgroundColor || "white"}
`;

const Button = (props: any): JSX.Element => {
    
    return (
        <>
            <StyledButton backgroundColor={ props.backgroundColor}>{ props.text }</StyledButton>
        </>
    )

}

export default Button;
