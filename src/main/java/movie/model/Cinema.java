package movie.model;

import java.util.HashMap;

public class Cinema {
    private int startTime;
    private int endTime;

    private char maxRow;
    private int maxColumn;

    private SeatGradeRule seatGradeRule;

    //                    row,    column
    private final HashMap<String, Seat[]> seats = new HashMap<>();

    public Cinema(int startTime, int endTime, char maxRow, int maxColumn, SeatGradeRule seatGradeRule) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.seatGradeRule = seatGradeRule;

        for (char i = 'A'; i <= maxRow; i++) {
            Seat[] _seat = new Seat[this.maxColumn];
            for (int j = 0; j < this.maxColumn; j++) {
                _seat[j] = new Seat(seatGradeRule.getSeatGrade(i, maxRow));
            }
            seats.put(String.valueOf(i), _seat);
        }
    }

    public HashMap<String, Seat[]> getSeats() {
        return seats;
    }

    public void addMovie(Movie movie) {
        movie.setSeats(seats);
    }
}
