import oop.ex2.SpaceShipPhysics;

import java.util.Random;

/**
 * This class represents a drunkard type of spaceship, and is a sub-class of Spaceship
 * will be automatic and controlled by the computer
 * This spaceship stupidly tries to bash other spaceships without shields on - and every rand time (not too long)
 * begins to spin in a circle for a rand time (not too long). very drunk driver
 *
 * @author lioraryepaz
 */

public class DrunkardSpaceShip extends SpaceShip {

    /**
     * internal counter to track when (in a random way) to begin being drunk
     */
    private int track = 0;

    /**
     * drunk mode factor
     */
    private boolean turnIsOn = false;

    private static final int RAND_FACTOR = 550;

    private final Random rand = new Random();

    /**
     * different doAction, because sometimes it will perform as normal and track rounds num + rand,
     * and sometimes will perform drunk actions
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        if (turnIsOn) {
            //drunk actions
            spaceShipPhysics.move(accelerateCheck(game), 1);
            shields = false;
            if (energyLevel < maximalEnergyLevel) {
                energyLevel++;
            }
            if (rand.nextInt(RAND_FACTOR) + 1 == RAND_FACTOR) {
                turnIsOn = false;
                track = 0;
            }
            //
        } else {
            super.doAction(game);
            track++;
            if (track > (rand.nextInt(RAND_FACTOR) + RAND_FACTOR)) {
                turnIsOn = true;
            }
        }
    }

    /**
     * decides whether to accelerate or not
     *
     * @param game the game object to which this ship belongs.
     * @return true, always, for this particular sub-class
     */
    @Override
    protected boolean accelerateCheck(SpaceWars game) {
        return true;
    }

    /**
     * decides whether and where to turn according to the closest ship - will always try to purse it.
     *
     * @param game the game object to which this ship belongs.
     * @return 1 for left turn, -1 for right turn, 0 for no turn
     */
    @Override
    protected int turnCheck(SpaceWars game) {
        SpaceShipPhysics nearSpaceShip = game.getClosestShipTo(this).getPhysics();
        Double angle = spaceShipPhysics.angleTo(nearSpaceShip);
        if (angle < 0) {
            return -1;
        } else if (angle > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
