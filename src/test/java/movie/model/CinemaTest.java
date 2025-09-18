package movie.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CinemaTest {
    @Test
    void 상영관_좌석_행에따라_좌석등급이_변경된다() {
        Cinema cinema = new Cinema(9, 20, 'F', 10, new DefaultSeatGradeRule());

        assertArrayEquals(
                List.of(SeatGrade.B,
                        SeatGrade.B,
                        SeatGrade.S,
                        SeatGrade.S,
                        SeatGrade.A,
                        SeatGrade.A
                ).toArray(),
                List.of(
                        cinema.getSeats().get("A")[0].getGrade(),
                        cinema.getSeats().get("B")[0].getGrade(),
                        cinema.getSeats().get("C")[0].getGrade(),
                        cinema.getSeats().get("D")[0].getGrade(),
                        cinema.getSeats().get("E")[0].getGrade(),
                        cinema.getSeats().get("F")[0].getGrade()
                ).toArray()
        );

        Cinema cinema2 = new Cinema(9, 20, 'E', 10, new DefaultSeatGradeRule());

        assertArrayEquals(
                List.of(SeatGrade.B,
                        SeatGrade.S,
                        SeatGrade.A,
                        SeatGrade.A,
                        SeatGrade.A
                ).toArray(),
                List.of(
                        cinema2.getSeats().get("A")[0].getGrade(),
                        cinema2.getSeats().get("B")[0].getGrade(),
                        cinema2.getSeats().get("C")[0].getGrade(),
                        cinema2.getSeats().get("D")[0].getGrade(),
                        cinema2.getSeats().get("E")[0].getGrade()
                ).toArray()
        );
    }

    @Test
    void 상영관에_영화를_추가할수있다() {
        Cinema cinema = new Cinema(6, 20, 'F', 10, new DefaultSeatGradeRule());
        Movie movie = new Movie("귀멸의칼날", 2);
        Movie movie2 = new Movie("귀멸의칼2", 4);
        try {
            cinema.addMovie(6, movie2);
            cinema.addMovie(10, movie);
            cinema.addMovie(12, movie);
            cinema.addMovie(14, movie2);
            cinema.addMovie(18, movie);
        } catch (Exception e) {
            fail("not expected error: " + e.getMessage());
        }
    }

    @Test
    void 상영관에_영화를_추가할때_겹치는_시간이_있으면_에러가_발생한다() {
        Cinema cinema = new Cinema(9, 20, 'F', 10, new DefaultSeatGradeRule());
        Movie movie = new Movie("테스트 영화", 2);
        try {
            cinema.addMovie(10, movie); // 10:00 ~ 12:00
        } catch (Exception e) {
            fail("not expected error: " + e.getMessage());
        }

        // 1. 이미 예약된 시간과 겹칠 때
        Exception exception = assertThrows(Exception.class, () -> {
            cinema.addMovie(11, new Movie("다른 영화", 2));
        });
        assertEquals("해당 시간에 상영 예정 영화가 존재합니다", exception.getMessage());

        // 2. 정확히 같은 시간에 시작할 때
        Exception exception2 = assertThrows(Exception.class, () -> {
            cinema.addMovie(10, new Movie("또 다른 영화", 1));
        });
        assertEquals("해당 시간에 상영 예정 영화가 존재합니다", exception2.getMessage());
    }

    @Test
    void 상영관_폐관시간을_넘겨_영화를_추가할_수_없다() {
        Cinema cinema = new Cinema(9, 20, 'F', 10, new DefaultSeatGradeRule());
        Movie movie = new Movie("테스트 영화", 3);

        Exception exception = assertThrows(Exception.class, () -> {
            cinema.addMovie(18, movie); // 18:00 + 3시간 = 21:00, 폐관시간 20:00 초과
        });
        assertEquals("상영관 폐관시간 보다 영화 상영시간이 깁니다.", exception.getMessage());
    }

    @Test
    void 올바르지_않은_시간에_영화를_추가할_수_없다() {
        Cinema cinema = new Cinema(9, 20, 'F', 10, new DefaultSeatGradeRule());
        Movie movie = new Movie("테스트 영화", 2);

        Exception exception = assertThrows(Exception.class, () -> {
            cinema.addMovie(21, movie);
        });
        assertEquals("올바르지 않은 시간입니다", exception.getMessage());
    }


}