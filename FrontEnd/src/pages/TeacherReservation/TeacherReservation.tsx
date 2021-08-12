import styled from "styled-components";
import TeacherCalender from "./TeacherCalender/TeacherCalender";
import TeacherReservationConfirm from "./TeacherReservationConfirm/TeacherReservationConfirm"
import '@fontsource/roboto';
import MainNavigation from "../../components/organisms/Main/Main-Navigation";
import TeacherTodoList from "./TeacherTodoList";
import axios from "axios";
const StyledDiv = styled.div`
   display: flex;
`;
const StyledColumnDiv = styled.div`
   display: flex;
   flex-direction: column;
`;

console.log(axios.post("https://i5b305.p.ssafy.io/api/user", {
    "email" : "nodaaw@naver.com",
    "password" : "asdasd",
    "username" : "싸움의고수",
    "nickname" : "내가선생",
    "gender": 0,
    "tel": "010-8860-1234",
    "zipCode": "12353",
    "street": "도로명",
    "detailedAddress": "상세주소",
    "role": "ROLE_PTTEACHER",
    "major" : "메이저",
    "certificates" : [
        {
            "name" : "자격증",
            "date" : "2019-11-12",
               "publisher" : "대한 협회"
        }
    ],
    "careers" : [
        {
            "company" : "ssafy",
            "startDate" : "2019-11-12",
               "endDate" : "2019-11-12",
            "role" : "왕"
        }
    ],
    "price" : 0,
    "description" : "나는 선생 너는 노예",
    "snsAddrs" : [
        {
            "url" : "아직",
               "platform" : "구현 안함"
        }
    ]
}));


const TeacherReservation = (): JSX.Element => {
    return (
        <>
            <StyledDiv>
                <StyledColumnDiv
                    >
                    <div style={{height: "10vh"}}>
                        <MainNavigation position="sticky" /> 
                    </div>
                    <TeacherReservationConfirm />
                </StyledColumnDiv>
                <StyledColumnDiv>
                    <TeacherCalender />
                    <TeacherTodoList />
                </StyledColumnDiv>
            </StyledDiv>
        </>
    );
}

export default TeacherReservation;