package movie.model;

import movie.model.seat.Seat;

import java.time.LocalDate;

public class Ticket {
    private Long userId;
    private final Movie movie;
    private final Seat seat;
    private final int startTime;
    private final int endTime;
    private LocalDate date;

    public Ticket(LocalDate date, Movie movie, Seat seat, int startTime, int endTime) {
        this.movie = movie;
        this.seat = seat;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
