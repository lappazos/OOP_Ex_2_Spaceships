/**
 * this is our objects factory class, and will create the different kinds of spaceship according to user input in
 * each and every game.
 * it will function as static class with out any instances.
 *
 * @author lioraryepaz
 */

public class SpaceShipFactory {

    /**
     * The denote of human
     */
    private static final String HUMAN = "h";

    /**
     * The denote of runner
     */
    private static final String RUNNER = "r";

    /**
     * The denote of basher
     */
    private static final String BASHER = "b";

    /**
     * The denote of aggressive
     */
    private static final String AGGRESSIVE = "a";

    /**
     * The denote of drunkard
     */
    private static final String DRUNKARD = "d";

    /**
     * The denote of special
     */
    private static final String SPECIAL = "s";

    /**
     * creates the different spaceship objects for the game
     *
     * @param args user input as array of strings representing the types of spaceship needed to be created
     * @return array of spaceships
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case HUMAN:
                    spaceShips[i] = new HumanSpaceShip();
                    break;
                case RUNNER:
                    spaceShips[i] = new RunnerSpaceShip();
                    break;
                case BASHER:
                    spaceShips[i] = new BasherSpaceShip();
                    break;
                case AGGRESSIVE:
                    spaceShips[i] = new AggressiveSpaceShip();
                    break;
                case SPECIAL:
                    spaceShips[i] = new SpecialSpaceShip();
                    break;
                case DRUNKARD:
                    spaceShips[i] = new DrunkardSpaceShip();
                    break;
            }
        }
        return spaceShips;
    }
}
