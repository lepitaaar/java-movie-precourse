package movie.model.pay;

import movie.model.Ticket;

import java.time.LocalDate;

public class PaymentSystem {

    public static boolean isMovieDay(LocalDate date) {
        int month = date.getDayOfMonth();

        // ex) 10, 20, 30
        return month % 10 == 0;
    }

    public static boolean isDiscountTime(int startTime) {
        return startTime <= 11 || startTime >= 20;
    }

    public static double discountAll(Ticket ticket, double amount) {
        if (isMovieDay(ticket.getDate())) {
            amount = amount * 0.9;
        }

        if (isDiscountTime(ticket.getStartTime())) {
            amount -= 2_000;
        }

        return amount;
    }

    public static long pay(Ticket ticket, int point, Payment payment) {
        double amount = ticket.getSeat().getGrade().getPrice();
        amount = discountAll(ticket, amount);

        amount -= point;

        amount = amount * (1 - payment.getDiscountRate());

        //실제로 유저에서 돈 차감되는 시스템 필요

        return Math.round(amount);
    }

    public static long pay(Ticket ticket, Payment payment) {
        double amount = ticket.getSeat().getGrade().getPrice();
        amount = discountAll(ticket, amount);

        amount = amount * (1 - payment.getDiscountRate());

        //실제로 유저에서 돈 차감되는 시스템 필요

        return Math.round(amount);
    }
}
