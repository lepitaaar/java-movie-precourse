package movie.model.seat;

public class DefaultSeatGradeRule implements SeatGradeRule {
    @Override
    public SeatGrade getSeatGrade(char row, char maxRow) {
        int rowValue = row - 'A';
        int maxRowValue = maxRow - 'A';
        int sectionSize = (maxRowValue + 1) / 3;

        if (rowValue < sectionSize) {
            return SeatGrade.B;
        } else if (rowValue < sectionSize * 2) {
            return SeatGrade.S;
        } else {
            return SeatGrade.A;
        }
    }
}
