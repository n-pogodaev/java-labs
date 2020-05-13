package App.Model;

public class FallenAngel extends Enemy {
    public FallenAngel(Knight knight, int positionX, int positionY, int maxPositionX) {
        super(knight, 100, 20, 100, 2, 140, positionX, positionY, maxPositionX);
    }
}
