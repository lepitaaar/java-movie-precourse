package movie.model;

import java.util.HashMap;

public class Cinema {
    private byte startTime;
    private byte endTime;

    private char maxRow;
    private int maxColumn;

    private SeatGradeRule seatGradeRule;

    //                    row,    column
    private final HashMap<String, Seat[]> seats = new HashMap<>();

    public Cinema(byte startTime, byte endTime, char maxRow, int maxColumn, SeatGradeRule seatGradeRule) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.seatGradeRule = seatGradeRule;

        for (int i = 'A'; i <= maxRow; i++) {
            Seat[] _seat = new Seat[this.maxColumn];
            for (int j = 0; j < this.maxColumn; j++) {
                _seat[j] = new Seat(seatGradeRule.getSeatGrade(String.valueOf((char) i), String.valueOf(maxRow)));
            }
            seats.put(String.valueOf((char) i), _seat);
        }
    }
}
