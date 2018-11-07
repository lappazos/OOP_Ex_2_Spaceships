import oop.ex2.SpaceShipPhysics;

/**
 * This class represents a basher type of spaceship, and is a sub-class of Spaceship
 * will be automatic and controlled by the computer
 * This spaceship attempts to collide with the nearest ship.
 *
 * @author lioraryepaz
 */

public class BasherSpaceShip extends SpaceShip {

    /**
     * The distance limit in order to activate the shields
     */
    private static final double DISTANCE_LIMIT = 0.19;

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
     * decides whether or not to activate the shields
     *
     * @param game the game object to which this ship belongs.
     * @return true if the closest ship is close as or less the distance limit, false otherwise
     */
    @Override
    protected boolean shieldCheck(SpaceWars game) {
        return (spaceShipPhysics.distanceFrom(game.getClosestShipTo(this).getPhysics()) <= DISTANCE_LIMIT);
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
