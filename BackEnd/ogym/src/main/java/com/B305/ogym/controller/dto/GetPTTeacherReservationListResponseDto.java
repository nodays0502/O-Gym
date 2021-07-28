package com.B305.ogym.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class GetPTTeacherReservationListResponseDto {
    private List<Reservation> teacherList;
    static class Reservation{
        private String username;
        private String nickname;
        private int gender;
        private String profile_url;
        private int price;
        private String sns_addrs;
        private String major;
        private float star_rating;
        private String description;
        private List<Career> careers ;
        private List<ReservationTime> alreadyReserveTime;

    }
    static class Career{

    }
    static class ReservationTime{
        private String reservationId;
        private LocalDateTime time;
    }
}
