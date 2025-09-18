package movie.model;

import movie.model.pay.Payment;
import movie.model.pay.PaymentSystem;

import java.util.ArrayList;

public class User {

    private final Long id;
    private int point = 0;

    ArrayList<Ticket> tickets = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }

    public void buyTicket(Ticket ticket, Payment payment, boolean usePoint) throws Exception {
        for (Ticket t : tickets) {
            int startTime = t.getStartTime();
            int endTime = t.getEndTime();
            
            if (ticket.getStartTime() >= startTime
                    && ticket.getStartTime() < endTime) throw new Exception("이미 해당 시간에 예매된 티켓이 있습니다");
        }

        if (usePoint) {
            PaymentSystem.pay(ticket, this.point, payment);
            minusPoint(this.point);
        }
        PaymentSystem.pay(ticket, payment);

        ticket.setUserId(id);
        tickets.add(ticket);
    }

    public Long getId() {
        return id;
    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void minusPoint(int point) {
        this.point -= point;
    }
}
