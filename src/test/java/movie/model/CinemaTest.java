package movie.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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

}