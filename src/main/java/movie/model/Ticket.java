package movie.model;

import movie.model.seat.Seat;

public class Ticket {
    private Long userId;
    private final Movie movie;
    private final Seat seat;
    private final int startTime;
    private final int endTime;

    public Ticket(Movie movie, Seat seat, int startTime, int endTime) {
        this.movie = movie;
        this.seat = seat;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
