package movie.model;

import movie.model.seat.Seat;

import java.util.HashMap;

public class Movie {
    private String title;
    private HashMap<String, Seat[]> seats;
    private int runningTime;

    public Movie(String title, int runningTime) {
        this.title = title;
        this.runningTime = runningTime;
    }

    public void setSeats(HashMap<String, Seat[]> seats) {
        this.seats = seats;
    }

    public Seat getSeat(String row, int column) {
        return this.seats.get(row)[column];
    }

    public int getRunningTime() {
        return this.runningTime;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
