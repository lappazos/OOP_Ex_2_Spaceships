import oop.ex2.GameGUI;

import java.awt.*;

/**
 * This class represents a human controlled spaceship, and is a sub-class of Spaceship
 *
 * @author lioraryepaz
 */

public class HumanSpaceShip extends SpaceShip {

    /**
     * decides whether to try teleport or not in every turn
     *
     * @param game the game object to which this ship belongs.
     * @return true if user wishes to teleport, false otherwise
     */
    @Override
    protected boolean teleportCheck(SpaceWars game) {
        return (game.getGUI()).isTeleportPressed();
    }

    /**
     * decides whether to accelerate or not
     *
     * @param game the game object to which this ship belongs.
     * @return true if user wishes to accelerate, false otherwise
     */
    @Override
    protected boolean accelerateCheck(SpaceWars game) {
        return (game.getGUI()).isUpPressed();
    }

    /**
     * decides whether and where to turn
     *
     * @param game the game object to which this ship belongs.
     * @return 1 for left turn, -1 for right turn, 0 for no turn
     */
    @Override
    protected int turnCheck(SpaceWars game) {
        if ((game.getGUI()).isLeftPressed()) {
            if ((game.getGUI()).isRightPressed()) {
                return 0;
            } else {
                return 1;
            }
        } else if ((game.getGUI()).isRightPressed()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * decides whether to try activate the shields or not
     *
     * @param game the game object to which this ship belongs.
     * @return true if user wishes to activate the shields, false otherwise
     */
    @Override
    protected boolean shieldCheck(SpaceWars game) {
        return (game.getGUI()).isShieldsPressed();
    }

    /**
     * decides whether to try and fire a shot
     *
     * @param game the game object to which this ship belongs.
     * @return true if the user wishes to shoot, false otherwise
     */
    @Override
    protected boolean fireCheck(SpaceWars game) {
        return (game.getGUI()).isShotPressed();
    }

    /**
     * Gets the image of this ship. This method returns the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    @Override
    public Image getImage() {
        if (shields) {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.SPACESHIP_IMAGE;
        }
    }
}
