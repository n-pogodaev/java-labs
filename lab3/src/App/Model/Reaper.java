package App.Model;

public class Reaper extends Enemy {
    public Reaper(Knight knight, int positionX, int positionY, int maxPositionX) {
        super(knight, 300, 100, 150, 2, 180,  positionX, positionY, maxPositionX);
    }
}
