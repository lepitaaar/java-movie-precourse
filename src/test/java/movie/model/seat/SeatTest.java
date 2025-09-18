package movie.model.seat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {
    @Test
    void S등급_좌석_가격이_맞는지_확인한다() {
        Seat seatS = new Seat(SeatGrade.S);
        assertEquals(18000, seatS.getGrade().getPrice());
    }

    @Test
    void A등급_좌석_가격이_맞는지_확인한다() {
        Seat seatA = new Seat(SeatGrade.A);
        assertEquals(15000, seatA.getGrade().getPrice());
    }

    @Test
    void B등급_좌석_가격이_맞는지_확인한다() {
        Seat seatB = new Seat(SeatGrade.B);
        assertEquals(12000, seatB.getGrade().getPrice());
    }
}