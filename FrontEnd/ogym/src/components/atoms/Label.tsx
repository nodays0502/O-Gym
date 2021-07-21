import React from 'react';
import Styled from 'styled-components'

interface LabelPropsType {
  color?: string;
  fontSize?: string;
}

const StyledLabel = Styled.label<LabelPropsType>`
  color: ${(props) => props.color || "#000000"};
  font-size: ${(props) => props.fontSize || "16px"};
`;

const Label = ( props: any):JSX.Element => {
  return (
    <StyledLabel color={props.color} fontSize={props.fontSize}>{props.label}</StyledLabel>
  );
};

export default Label;