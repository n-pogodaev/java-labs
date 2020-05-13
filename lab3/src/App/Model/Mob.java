package App.Model;

import App.Utils.Direction;
import App.Utils.States;

public abstract class Mob {
    private final int speed;
    private int health;
    private int attack;
    private int armor;
    private Direction direction;
    private int positionX;
    private int maxPositionX = 0;
    private final int positionY;
    private States state = States.IDLE;
    private final int attackRange;

    protected Mob(int health, int attack, int armor, int speed, int attackRange,
                  int positionX, int positionY, int maxPositionX) {
        this.health = health;
        this.attack = attack;
        this.armor = armor;
        this.speed = speed;
        direction = Direction.RIGHT;
        this.positionX = positionX;
        this.attackRange = attackRange;
        this.positionY = positionY;
        this.maxPositionX = maxPositionX;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    protected void setHealth(int value) {
        health = value;
    }

    protected void setArmor(int value) {
        armor = value;
    }

    protected void setAttack(int value) {
        attack = value;
    }

    public boolean isDied() {
        return health <= 0;
    }

    public void setDirection(Direction d) {
        direction = d;
    }

    public Direction getDirection() {
        return direction;
    }

    public void takeDamage(int damageNum) {
        int damageAfterArmor;
        if (armor - damageNum <= 0) {
            armor = 0;
            damageAfterArmor = damageNum - armor;
        }
        else {
            armor -= damageNum;
            return;
        }
        health -= damageAfterArmor;
    }

    public int getPositionX() {
        return positionX;
    }

    protected void setPositionX(int value) {
        positionX = value;
    }

    public int getPositionY() {
        return positionY;
    }

    public States getState() {
        return state;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setState(States state) {
        if (this.state != States.DIED || state == States.DIED) {
            this.state = state;
        }
    }

    private void moveLeft() {
        if (positionX - speed <= 0) {
            positionX = 0;
        }
        else {
            positionX -= speed;
        }
    }

    private void moveRight() {
        if (positionX + speed >= maxPositionX) {
            positionX = maxPositionX;
        }
        else {
            positionX += speed;
        }
    }

    public void move() {
        switch (direction) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    public void update() {
        if (state == States.MOVING) {
            move();
        }
    }

    public void setRightBorder(int position) {
        maxPositionX = position;
    }

    public abstract void reset();

    public abstract void resetPositionX();
}
