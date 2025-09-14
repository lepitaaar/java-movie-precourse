package movie.model;

import java.util.HashMap;

public class Cinema {
    private byte startTime;
    private byte endTime;
    private char endRow;
    private int column;

    //                    row,    column
    private final HashMap<String, Seat[]> seats = new HashMap<>();

    public Cinema(byte startTime, byte endTime, char endRow, int column) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.endRow = endRow;
        this.column = column;

        for (int i = 'A'; i <= endRow; i++) {
            seats.put(String.valueOf((char) i), new Seat[this.column]);
        }
    }
}
