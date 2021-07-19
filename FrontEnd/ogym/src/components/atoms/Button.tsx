import React from 'react';
import { useRecoilState } from 'recoil';
import { ButtonState } from '../../recoil/atoms/Button';


const Button = (props: any): JSX.Element => {
    
    return (
        <>
            <button>{ props.text }</button>
        </>
    )
}

export default Button;
