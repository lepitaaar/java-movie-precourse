package movie.model;

public class User {

    private int point = 0;

    public User() {}

    public void reserveMovie(String movieTitle, int time) {

    }

    public int getPoint() {
        return point;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void minusPoint(int point) {
        this.point -= point;
    }
}
