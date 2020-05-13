package App.Model;

public class Golem extends Enemy {
    public Golem(Knight knight, int positionX, int positionY, int maxPositionX) {
        super(knight, 250, 15, 0, 2, 100, positionX, positionY, maxPositionX);
    }
}
