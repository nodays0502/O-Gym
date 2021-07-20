import styled from "styled-components";
import { ButtonPropsType } from "../atoms/Button";
import { InputPropsType } from "../atoms/Input";


interface ListItemProps {
    children: React.ReactElement<ButtonPropsType> | React.ReactElement<InputPropsType>[]
}

const StyledListItem = styled.div`

    display: flex;
    justify-content: center;
    
`;

const ListItem = ({ children, }: ListItemProps) => {
    
    return (<StyledListItem>
        { children }
    </StyledListItem>);

};

export default ListItem;