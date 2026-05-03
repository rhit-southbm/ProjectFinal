package model;

public class GameModel {
    private Ball enemy = new Ball(100, 100);
    private Player player = new Player(300, 300);

    public void update() {
        enemy.update(); // Only enemy moves automatically
    }

    public Ball getEnemy() { return enemy; }
    public Player getPlayer() { return player; }
}
