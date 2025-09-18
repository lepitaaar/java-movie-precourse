package movie.model.pay;

import movie.model.Movie;
import movie.model.Ticket;
import movie.model.seat.Seat;
import movie.model.seat.SeatGrade;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentSystemTest {

    @Test
    void 할인_적용_안될때() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 12;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        double amount = seat.getGrade().getPrice();

        double discountedAmount = PaymentSystem.discountAll(ticket, amount);

        assertEquals(18000, discountedAmount);
    }

    @Test
    void 무비데이_할인적용() {
        LocalDate date = LocalDate.of(2024, 1, 10);
        int startTime = 12;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        double amount = seat.getGrade().getPrice();

        double discountedAmount = PaymentSystem.discountAll(ticket, amount);

        assertEquals(seat.getGrade().getPrice() * 0.9, discountedAmount);
    }

    @Test
    void 조조할인_적용() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 10;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        double amount = seat.getGrade().getPrice();

        double discountedAmount = PaymentSystem.discountAll(ticket, amount);

        assertEquals(seat.getGrade().getPrice() - 2000, discountedAmount);
    }

    @Test
    void 심야할인_적용() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 22;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        double amount = seat.getGrade().getPrice();

        double discountedAmount = PaymentSystem.discountAll(ticket, amount);

        assertEquals(seat.getGrade().getPrice() - 2000, discountedAmount);
    }

    @Test
    void 영화의날_조조할인_중복적용() {
        LocalDate date = LocalDate.of(2024, 1, 10);
        int startTime = 10;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        double amount = seat.getGrade().getPrice();

        double discountedAmount = PaymentSystem.discountAll(ticket, amount);

        assertEquals(seat.getGrade().getPrice() * 0.9 - 2000, discountedAmount);
    }

    @Test
    void 포인트_결제_테스트() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 12;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        int point = 1000;
        Payment payment = Payment.CreditCard;

        long finalAmount = PaymentSystem.pay(ticket, point, payment);

        assertEquals(Math.round((seat.getGrade().getPrice() - 1000) * (1 - payment.getDiscountRate())), finalAmount);
    }

    @Test
    void 신용카드_결제_할인_테스트() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 12;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        int point = 0;
        Payment payment = Payment.CreditCard;

        long finalAmount = PaymentSystem.pay(ticket, point, payment);

        assertEquals(Math.round(seat.getGrade().getPrice() * (1 - payment.getDiscountRate())), finalAmount);
    }

    @Test
    void 현금_결제_할인_테스트() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        int startTime = 12;
        Seat seat = new Seat(SeatGrade.S);
        Movie movie = new Movie("test", 2);
        Ticket ticket = new Ticket(date, movie, seat, startTime, startTime + movie.getRunningTime());
        int point = 0;
        Payment payment = Payment.Cash;

        long finalAmount = PaymentSystem.pay(ticket, point, payment);

        assertEquals(Math.round(seat.getGrade().getPrice() * (1 - payment.getDiscountRate())), finalAmount);
    }
}