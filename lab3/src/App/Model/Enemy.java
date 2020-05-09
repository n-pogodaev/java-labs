package App.Model;

import App.Utils.Direction;

public abstract class Enemy extends Mob {
    private final int enemyHealth;
    private final int enemyArmor;
    private final int enemyAttack;
    private final int startPositionX;

    protected Enemy(int health, int attack, int armor, int speed, int attackRange,
                    int positionX, int positionY, int maxPositionX) {
        super(health, attack, armor, speed, attackRange, positionX, positionY, maxPositionX);
        enemyArmor = armor;
        enemyAttack = attack;
        enemyHealth = health;
        startPositionX = positionX;
    }

    @Override
    public void reset() {
        setHealth(enemyHealth);
        setAttack(enemyAttack);
        setArmor(enemyArmor);
        setDirection(Direction.RIGHT);
    }

    @Override
    public void resetPositionX() {
        setPositionX(startPositionX);
    }
}
