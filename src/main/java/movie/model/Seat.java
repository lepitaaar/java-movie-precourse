package movie.model;

public class Seat {
    private final SeatGrade grade;

    public Seat(SeatGrade grade) {
        this.grade = grade;
    }

    public SeatGrade getGrade() {
        return grade;
    }
}
