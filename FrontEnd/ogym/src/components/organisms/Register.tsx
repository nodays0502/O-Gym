import React from 'react';
import styled from 'styled-components';
import Label from '../atoms/Label';
import Input from '../atoms/Input';

const Wrapper = styled.div`
  & + & {
    margin-top: 1rem;
  }
`;

const Register = () => {
  return (
    <Wrapper>
      <Label label="이메일"/>
      <Input type="email" placeholder="이메일 주소를 입력해주세요"/>
    </Wrapper>
  );
};

export default Register;