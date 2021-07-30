import { atom } from "recoil";

interface InputStateType {
  [inputType: string]: string | number;
}

export const InputState = atom<InputStateType>({
  key: 'InputState',
  default: {
    loginEmail: '',
    loginPassword: '',
    registerPassword: '',
    registerPasswordConfirmation: '',
    registerName: '',
    registerNickname: '',
    registerEmail: '',
    registerPhone: '',
    registerGender: 0,
    registerZipcode: '',
    registerStreetAddress: '',
    registerDetailedAddress: '',
    

  }
})