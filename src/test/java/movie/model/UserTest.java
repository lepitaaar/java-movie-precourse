package movie.model;

import movie.model.pay.Payment;
import movie.model.seat.Seat;
import movie.model.seat.SeatGrade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("성공적인 티켓 구매 테스트")
    void buyTicket_success() throws Exception {
        User user = new User(1L);
        Movie movie = new Movie("테스트 영화", 2);
        Seat seat = new Seat(SeatGrade.S);
        Ticket ticket = new Ticket(LocalDate.now(), movie, seat, 14, 16);

        user.buyTicket(ticket, Payment.CreditCard, false);

        assertTrue(user.tickets.contains(ticket));
        assertEquals(1, user.tickets.size());
        assertEquals(user.getId(), ticket.getUserId());
        assertEquals((int) (seat.getGrade().getPrice() * 0.01), user.getPoint());
    }

    @Test
    @DisplayName("포인트를 사용한 티켓 구매 테스트")
    void buyTicket_withPoints() throws Exception {
        User user = new User(1L);
        user.addPoint(2000);
        Movie movie = new Movie("테스트 영화", 2);
        Seat seat = new Seat(SeatGrade.A);
        Ticket ticket = new Ticket(LocalDate.now(), movie, seat, 18, 20);

        user.buyTicket(ticket, Payment.CreditCard, true);

        assertTrue(user.tickets.contains(ticket));
        int expectedPoints = (int) (seat.getGrade().getPrice() * 0.01);
        assertEquals(expectedPoints, user.getPoint());
    }

    @Test
    @DisplayName("티켓 시간 중복 시 예외 발생 테스트")
    void buyTicket_overlappingTime_throwsException() throws Exception {
        User user = new User(1L);
        Movie movie = new Movie("테스트 영화", 2);
        Seat seat = new Seat(SeatGrade.S);

        Ticket ticket1 = new Ticket(LocalDate.now(), movie, seat, 14, 16);
        user.buyTicket(ticket1, Payment.CreditCard, false);

        Ticket ticket2 = new Ticket(LocalDate.now(), movie, seat, 15, 17);

        Exception exception = assertThrows(Exception.class, () -> {
            user.buyTicket(ticket2, Payment.CreditCard, false);
        });
        assertEquals("이미 해당 시간에 예매된 티켓이 있습니다", exception.getMessage());
        assertEquals(1, user.tickets.size());
    }

    @Test
    @DisplayName("포인트 적립 테스트")
    void buyTicket_pointAccumulation() throws Exception {
        User user = new User(1L);
        Movie movie = new Movie("테스트 영화", 2);
        Seat seatS = new Seat(SeatGrade.S);
        Seat seatA = new Seat(SeatGrade.A);

        Ticket ticket1 = new Ticket(LocalDate.now(), movie, seatS, 10, 12);
        Ticket ticket2 = new Ticket(LocalDate.now(), movie, seatA, 14, 16);

        int expectedPoints1 = (int) (seatS.getGrade().getPrice() * 0.01);
        int expectedPoints2 = (int) (seatA.getGrade().getPrice() * 0.01);

        user.buyTicket(ticket1, Payment.Cash, false);
        assertEquals(expectedPoints1, user.getPoint());
        assertEquals(1, user.tickets.size());

        user.buyTicket(ticket2, Payment.CreditCard, false);
        assertEquals(expectedPoints1 + expectedPoints2, user.getPoint());
        assertEquals(2, user.tickets.size());
    }
}