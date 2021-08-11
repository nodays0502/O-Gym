import styled from "styled-components";
import TeacherCalender from "./TeacherCalender/TeacherCalender";
import TeacherReservationConfirm from "./TeacherReservationConfirm/TeacherReservationConfirm"
import '@fontsource/roboto';
const StyledDiv = styled.div`
   display: flex;
`;

const TeacherReservation = (): JSX.Element => {
    return (

        <>
                <TeacherReservationConfirm />
                {/* <TeacherCalender /> */}
        </>

    );
}

export default TeacherReservation;