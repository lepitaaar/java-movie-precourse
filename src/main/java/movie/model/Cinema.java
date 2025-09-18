package movie.model;

import movie.model.seat.Seat;
import movie.model.seat.SeatGradeRule;

import java.time.LocalDate;
import java.util.HashMap;

public class Cinema {
    private int openTime;
    private int closedTime;

    private char maxRow;
    private int maxColumn;

    private SeatGradeRule seatGradeRule;

    //                    row,    column
    private final HashMap<String, Seat[]> seats = new HashMap<>();

    private final HashMap<LocalDate, Movie[]> schedule = new HashMap<>();

    public Cinema(int openTime, int closedTime, char maxRow, int maxColumn, SeatGradeRule seatGradeRule) {
        this.openTime = openTime;
        this.closedTime = closedTime;
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

    public void addMovie(int year, int month, int day, int startTime, Movie movie) throws Exception {
        LocalDate date = LocalDate.of(year, month, day);
        Movie[] dailySchedule = schedule.computeIfAbsent(date, k -> new Movie[closedTime - openTime]);

        int scheduleIndex = Math.abs(startTime - openTime);
        if (dailySchedule.length < scheduleIndex) throw new Exception("올바르지 않은 시간입니다");
        if (dailySchedule[scheduleIndex] != null) throw new Exception("해당 시간에 상영 예정 영화가 존재합니다");

        for (int i = 0; i < scheduleIndex; i++) {
            if (dailySchedule[i] == null) continue;
            int runningTime = dailySchedule[i].getRunningTime();
            int endTime = openTime + i + runningTime;

            if (startTime < endTime) {
                throw new Exception("해당 시간에 상영 예정 영화가 존재합니다");
            }
        }

        if (startTime + movie.getRunningTime() > closedTime) throw new Exception("상영관 폐관시간 보다 영화 상영시간이 깁니다.");

        movie.setSeats(seats);
        dailySchedule[scheduleIndex] = movie;
    }
}
