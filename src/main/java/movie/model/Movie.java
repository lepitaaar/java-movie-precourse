package movie.model;

import java.util.HashMap;

public class Movie {
    private String title;
    private HashMap<String, Seat[]> seats;

    public Movie(String title) {
        this.title = title;
    }

    public void setSeats(HashMap<String, Seat[]> seats) {
        this.seats = seats;
    }


}
