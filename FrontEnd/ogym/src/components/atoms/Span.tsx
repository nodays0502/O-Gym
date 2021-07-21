import React from 'react';
import Styled from 'styled-components'

interface SpanPropsType {
  color?: string;
  fontSize?: string;
  backgroundColor?: string;
}

const StyledSpan = Styled.span<SpanPropsType>`
  color: ${(props) => props.color || "#000000"};
  font-size: ${(props) => props.fontSize || "16px"};
  background-color: ${(props) => props.backgroundColor || "transparent"};
`;

const Span = (props: any): JSX.Element => {
  return (
    <StyledSpan 
      color={props.color} 
      fontSize={props.fontSize}
      backgroundColor={props.backgroundColor}
    >
      {props.span} 
    </StyledSpan>
  );
};

export default Span;