import oop.ex2.SpaceShipPhysics;

/**
 * This class represents an aggressive type of spaceship, and is a sub-class of Spaceship
 * will be automatic and controlled by the computer
 * This spaceship purse other ships and tries to fire at them.
 *
 * @author lioraryepaz
 */

public class AggressiveSpaceShip extends SpaceShip {

    /**
     * The distance limit in order to activate the shields
     */
    private static final double ANGLE_LIMIT = 0.21;

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

    /**
     * check if the nearest ship is within range and fire at it if so
     *
     * @param game the game object to which this ship belongs.
     * @return true if in range, false otherwise
     */
    @Override
    protected boolean fireCheck(SpaceWars game) {
        return spaceShipPhysics.angleTo(game.getClosestShipTo(this).getPhysics()) < ANGLE_LIMIT;
    }
}
