import oop.ex2.SpaceShipPhysics;

/**
 * This class represents a runner type of spaceship, and is a sub-class of Spaceship
 * will be automatic and controlled by the computer
 * This spaceship attempts to run away from the fight.
 *
 * @author lioraryepaz
 */

public class RunnerSpaceShip extends SpaceShip {

    /**
     * The teleport distance limit
     */
    private static final double TELEPORT_DISTANCE_LIMIT = 0.25;

    /**
     * The teleport angle limit
     */
    private static final double TELEPORT_ANGLE_LIMIT = 0.23;

    /**
     * decides whether to try teleport or not in every turn
     *
     * @param game the game object to which this ship belongs.
     * @return true if the spaceship feels threatened, false otherwise
     */
    @Override
    protected boolean teleportCheck(SpaceWars game) {
        SpaceShipPhysics nearSpaceShip = game.getClosestShipTo(this).getPhysics();
        return ((spaceShipPhysics.distanceFrom(nearSpaceShip) < TELEPORT_DISTANCE_LIMIT) &&
                (spaceShipPhysics.angleTo(nearSpaceShip) < TELEPORT_ANGLE_LIMIT));
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
     * decides whether and where to turn according to the closest ship - will always try to run from.
     *
     * @param game the game object to which this ship belongs.
     * @return 1 for left turn, -1 for right turn, 0 for no turn
     */
    @Override
    protected int turnCheck(SpaceWars game) {
        SpaceShipPhysics nearSpaceShip = game.getClosestShipTo(this).getPhysics();
        Double angle = spaceShipPhysics.angleTo(nearSpaceShip);
        if (angle < 0) {
            return 1;
        } else if (angle > 0) {
            return -1;
        } else {
            return 1;
        }
    }

}
