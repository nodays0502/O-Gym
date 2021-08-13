import * as React from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import styled from "styled-components";
import axios from "axios";


const ErrorP = styled.p`
  color: red;
`;

interface FormValues {
  email: string;
  password: string;

}

const schema = yup.object().shape({
  email: yup.string()
    .required("이메일을 입력해주세요")
    .max(30, "올바른 이메일 형식이 아닙니다")
    .matches(/[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i, "올바른 이메일 형식이 아닙니다"),
  password: yup.string()
    .required("비밀번호를 입력해주세요")
    .min(8, "비밀번호는 8자 이상 입력해주세요")
    .max(20, "비밀번호는 20자 이하 입력해주세요"),
});

function Login() {
  
  const { register, setValue, handleSubmit, formState: {errors} } = useForm<FormValues>({
    resolver: yupResolver(schema)
  });

  const onSubmit = (data: FormValues) => {
    axios.post('https://i5b305.p.ssafy.io/api/authenticate', data)
    .then(response => {
      const { accessToken } = response.data.data;
      console.log(accessToken)

      axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
    })
  }

  return (
    <form onSubmit={handleSubmit(onSubmit)} >
      <label htmlFor="email">이메일</label>
      <input type="text" placeholder="이메일"{...register("email")} maxLength={30}/>
      {errors.email?.message && <ErrorP>{errors.email?.message}</ErrorP>}

      <label htmlFor="password">비밀번호</label>
      <input type="password" placeholder="비밀번호(8~20자)" {...register("password")} maxLength={20} />
      {errors.password?.message && <ErrorP>{errors.password?.message}</ErrorP>}
      
      <input 
        type="submit" 
        placeholder="로그인"
      />
    </form>
  );
}

export default Login;