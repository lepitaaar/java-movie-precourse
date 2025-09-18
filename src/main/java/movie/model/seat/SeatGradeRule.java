package movie.model.seat;

public interface SeatGradeRule {
    SeatGrade getSeatGrade(char row, char maxRow);
}
