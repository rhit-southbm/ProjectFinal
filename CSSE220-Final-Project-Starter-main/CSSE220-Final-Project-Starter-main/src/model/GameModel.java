package model;

public class GameModel {
    private Ball ball = new Ball(100, 100);

    public void update() {
        ball.update();
    }

    public Ball getBall() {
        return ball;
    }
}


