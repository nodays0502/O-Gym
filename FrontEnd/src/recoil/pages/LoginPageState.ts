import { atom } from "recoil";


export const ModalState = atom < boolean >({
    key: 'modalState',
    default: true
})

export const LoginTokenState = atom<object>({
    key: 'loginAccessToken',
    default: {
        'accessToken' : '',
        'refreshToken' : '',
    }
})
