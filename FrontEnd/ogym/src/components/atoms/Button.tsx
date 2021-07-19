import React from 'react';
import { atom } from 'recoil';

export const Button = atom({
    key: 'textState', // unique ID (with respect to other atoms/selectors)
    default: '', // default value (aka initial value)
  });

// export default Button;
