import React from 'react';
import { useRecoilState } from 'recoil';
import { InputState } from '../../recoil/atoms/InputState';

export interface Props {
  type: string
  value?: string | number
  inputName?: string
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void
  placeholder?: string

}

function Input({ type,  ...props }: Props): JSX.Element {
  const [text, setText] = useRecoilState(InputState);

  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    console.log(event.target.value)
    setText(event.target.value);
  }

  return <input type={type} value={text} onChange={onChange} />;
}

export default Input; 