package App.Model;

import App.Utils.Direction;
import App.Utils.States;

public abstract class Enemy extends Mob {
    private final int enemyHealth;
    private final int enemyArmor;
    private final int enemyAttack;
    private final int startPositionX;
    private final Knight knight;

    protected Enemy(Knight knight, int health, int attack, int armor, int speed, int attackRange,
                    int positionX, int positionY, int maxPositionX) {
        super(health, attack, armor, speed, attackRange, positionX, positionY, maxPositionX);
        this.knight = knight;
        enemyArmor = armor;
        enemyAttack = attack;
        enemyHealth = health;
        startPositionX = positionX;
        setDirection(Direction.LEFT);
        setState(States.MOVING);
    }

    @Override
    public void reset() {
        setHealth(enemyHealth);
        setAttack(enemyAttack);
        setArmor(enemyArmor);
        setPositionX(startPositionX);
        setDirection(Direction.LEFT);
        setState(States.MOVING);
    }

    public boolean attack() {
        int distance = knight.getPositionX() - getPositionX();
        if (getDirection() == Direction.RIGHT && distance > 0 && distance <= getAttackRange() ||
                getDirection() == Direction.LEFT && distance < 0 && -distance <= getAttackRange()) {
            knight.takeDamage(getAttack());
            if (knight.isDied()) {
                knight.setState(States.DIED);
            }
            else {
                knight.setState(States.TAKING_DAMAGE);
            }
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if (!isDied()) {
            if (knight.getPositionX() - getPositionX() >= 0) {
                setDirection(Direction.RIGHT);
            } else {
                setDirection(Direction.LEFT);
            }
        }
        super.update();
    }

    @Override
    public void resetPositionX() {
        setPositionX(startPositionX);
    }
}
