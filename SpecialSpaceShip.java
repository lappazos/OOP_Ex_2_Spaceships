
/**
 * This class represents a special type of spaceship, and is a sub-class of Spaceship
 * will be automatic and controlled by the computer
 * This spaceship track the closest spaceship in his first moment of birth in the first round, and then will purse,
 * shoot and bash only him throughout the entire game, even if he dies and rebirth - he is his sworn enemy.
 *
 * @author lioraryepaz
 */

public class SpecialSpaceShip extends SpaceShip {

    /**
     * The closest spaceship in the object moment of birth - it will actually be final once it initialized
     */
    private SpaceShip nearestShipInStart;

    /**
     * The distance limit in order to activate the shields
     */
    private static final double ANGLE_LIMIT = 0.21;

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
     * decides whether and where to turn according to the closest ship in start - will always try to purse it.
     *
     * @param game the game object to which this ship belongs.
     * @return 1 for left turn, -1 for right turn, 0 for no turn
     */
    @Override
    protected int turnCheck(SpaceWars game) {
        // The following if will only happen once - in the first round. i know this is not the way to do so - the proper
        // way will be passing it as an argument in the constructor and performing this action of finding the nearest
        // ship in start as part from the constructor - a 'target' fo life time, sworn enemy.
        // but, because i couldn't change the SpaceWars class, this is the patch i performed.
        if (nearestShipInStart == null) {
            nearestShipInStart = game.getClosestShipTo(this);
        }
        Double angle = spaceShipPhysics.angleTo(nearestShipInStart.getPhysics());
        if (angle < 0) {
            return -1;
        } else if (angle > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * check if the nearest ship in start is within range and fire at it if so
     *
     * @param game the game object to which this ship belongs.
     * @return true if in range, false otherwise
     */
    @Override
    protected boolean fireCheck(SpaceWars game) {
        return spaceShipPhysics.angleTo(nearestShipInStart.getPhysics()) < ANGLE_LIMIT;
    }

    /**
     * decides whether or not to activate the shields
     *
     * @param game the game object to which this ship belongs.
     * @return true if the closest ship is close as or less the distance limit, false otherwise
     */
    @Override
    protected boolean shieldCheck(SpaceWars game) {
        return (spaceShipPhysics.distanceFrom(nearestShipInStart.getPhysics()) <= DISTANCE_LIMIT);
    }
}
