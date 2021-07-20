import { atom } from "recoil";

export const InputState = atom<string>({
  key: 'InputState',
  default: '',
})