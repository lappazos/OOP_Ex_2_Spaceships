import java.awt.Image;

import oop.ex2.*;

/**
 * This class represents an abstract object of spaceship with all of the qualities a spaceship need to have,
 * and will be used as super-class for all of the other types of spaceship classes.
 *
 * @author lioraryepaz
 */
public abstract class SpaceShip {

    /*
     * an sdd-on to the maxEnergyLevel as well as the energyLevel
     */
    private static final int ENERGY_INDUCER = 18;

    /*
     * a deduction to the maxEnergyLevel after being hit by collision
     */
    private static final int MAX_ENERGY_INDUCER = 10;

    /**
     * The instance of the ship's location, speed and direction
     */
    protected SpaceShipPhysics spaceShipPhysics;

    /*
    The initialized energy level
     */
    private static final int INIT_ENERGY_LEVEL = 190;

    /**
     * The current energyLevel of a spaceShip
     */
    public int energyLevel;

    /*
    The initialized max energy level
     */
    private static final int INIT_MAX_ENERGY_LEVEL = 210;

    /**
     * The maximum energyLevel a spaceShip can obtain
     */
    public int maximalEnergyLevel;

    /*
    The initial health of just born ship
     */
    private static final int MAX_HEALTH = 22;

    /**
     * The amount of lives a spaceShip has before it dies
     */
    public int healthLevel;

    /*
     * The energy cost of teleport
     */
    private static final int TELEPORT_COST = 140;

    /*
     * The energy cost of shields
     */
    private static final int SHIELD_COST = 3;

    /*
     * The energy cost of fire shot
     */
    private static final int FIRE_SHOT_COST = 19;

    /*
     * Whether the spaceShip shields are on or not
     */
    protected boolean shields = false;

    /*
     * Rounds period of coolDown
     */
    private static final int COOL_DOWN_PERIOD = 7;

    /*
     * Fire cool-down period counter
     */
    private int coolDownCounter = COOL_DOWN_PERIOD;

    /**
     * If coolDownCounter is activated, according to has a shot been fired or not
     */
    public boolean coolDownActive;

    /**
     * Constructor
     */
    public SpaceShip() {
        reset();
    }

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        if (teleportCheck(game)) {
            teleport();
        }
        spaceShipPhysics.move(accelerateCheck(game), turnCheck(game));
        shields = false;
        if (shieldCheck(game)) {
            shieldOn();
        }
        if (fireCheck(game)) {
            fire(game);
        }
        coolDownManager();
        if (energyLevel < maximalEnergyLevel) {
            energyLevel++;
        }
    }

    /*
     *manage the cool down process
     */
    private void coolDownManager() {
        if (coolDownActive) {
            coolDownCounter--;
            if (coolDownCounter < 1) {
                coolDownActive = false;
            }
        }
    }

    /**
     * Every spaceShip type decides on every round whether to teleport or not according to its own behaviour
     *
     * @param game the game object to which this ship belongs.
     * @return false as default if the method hasn't been run down by sub-class
     */
    protected boolean teleportCheck(SpaceWars game) {
        return false;
    }

    /**
     * Every spaceShip type decides on every round its movements according to its own behaviour
     *
     * @param game the game object to which this ship belongs.
     * @return false as default if the method hasn't been run down by sub-class
     */
    protected boolean accelerateCheck(SpaceWars game) {
        return false;
    }

    /**
     * Every spaceShip type decides on every round its movements according to its own behaviour
     *
     * @param game the game object to which this ship belongs.
     * @return 0 as default if the method hasn't been run down by sub-class
     */
    protected int turnCheck(SpaceWars game) {
        return 0;
    }

    /**
     * Every spaceShip type decides on every round its shield activation according to its own behaviour
     *
     * @param game the game object to which this ship belongs.
     * @return false as default if the method hasn't been run down by sub-class
     */
    protected boolean shieldCheck(SpaceWars game) {
        return false;
    }

    /**
     * Every spaceShip type decides on every round whether to fire a shot according to its own behaviour
     *
     * @param game the game object to which this ship belongs.
     * @return false as default if the method hasn't been run down by sub-class
     */
    protected boolean fireCheck(SpaceWars game) {
        return false;
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (shields) {
            energyLevel += ENERGY_INDUCER;
            maximalEnergyLevel += ENERGY_INDUCER;
        } else {
            hitManager();
        }
    }

    /**
     * in charge to the process of getting hit by shot or collision
     */
    private void hitManager() {
        healthLevel--;
        maximalEnergyLevel -= MAX_ENERGY_INDUCER;
        if (energyLevel > maximalEnergyLevel) {
            energyLevel = maximalEnergyLevel;
        }
    }

    /**
     * This method is called whenever uua ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        spaceShipPhysics = new SpaceShipPhysics();
        healthLevel = MAX_HEALTH;
        maximalEnergyLevel = INIT_MAX_ENERGY_LEVEL;
        energyLevel = INIT_ENERGY_LEVEL;
        coolDownActive = false;
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return healthLevel < 1;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return spaceShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shields) {
            hitManager();
        }
    }

    /**
     * Gets the image of this ship. This method returns the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (shields) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (energyLevel >= FIRE_SHOT_COST) {
            if (!coolDownActive) {
                game.addShot(spaceShipPhysics);
                energyLevel -= FIRE_SHOT_COST;
                coolDownActive = true;
                coolDownCounter = COOL_DOWN_PERIOD;
            }
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (energyLevel >= SHIELD_COST) {
            shields = true;
            energyLevel -= SHIELD_COST;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (energyLevel >= TELEPORT_COST) {
            spaceShipPhysics = new SpaceShipPhysics();
            energyLevel -= TELEPORT_COST;
        }
    }
}
