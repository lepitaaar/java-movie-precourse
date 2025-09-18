package movie.model.seat;

public class Seat {
    private final SeatGrade grade;

    public Seat(SeatGrade grade) {
        this.grade = grade;
    }

    public SeatGrade getGrade() {
        return grade;
    }
}
