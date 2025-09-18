package movie.model;

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

    public int getRunningTime() {
        return this.runningTime;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return true;
//    }
}
