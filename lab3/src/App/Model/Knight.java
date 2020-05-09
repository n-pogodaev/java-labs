package App.Model;

import App.Utils.Direction;

public class Knight extends Mob {
    private static final int maxHealth = 200;
    private static final int maxArmor = 150;
    private static final int maxAttack = 200;

    public Knight(int positionY, int maxPositionX) {
        super(maxHealth / 2, maxAttack / 4, maxArmor / 3,
                4, 50, 0, positionY, maxPositionX);
    }

    @Override
    public void reset() {
        setHealth(maxHealth);
        setAttack(maxAttack / 4);
        resetPositionX();
        setArmor(maxArmor / 5);
        setDirection(Direction.RIGHT);
    }

    @Override
    public void resetPositionX() {
        setPositionX(0);
    }

    public void regenerateHealth(int regenNum) {
        setHealth(Math.min(getHealth() + regenNum, maxHealth));
    }

    public void increaseAttack(int increaseNum) {
        setAttack(Math.min(getAttack() + increaseNum, maxAttack));
    }

    public void restoreArmor(int restoreNum) {
        setArmor(Math.min(getArmor() + restoreNum, maxArmor));
    }
}