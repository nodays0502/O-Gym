package com.B305.ogym.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PTDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReservationRequestDto {

        @NotNull
        private int ptTeacherId;

        @NotEmpty
        private LocalDateTime reservationTime;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReservationCancelRequestDto {
        @NotNull
        private int reservationId;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TeacherListRequestDto {
        @NotEmpty
        private List<Map<String,String>> filter;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TeacherReservationListResponseDto {
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


}
