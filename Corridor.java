package escape;

/**
 * Representation of a corridor between two rooms. Points to two rooms to its left and right, 
 * or above and below. Also stores whether or not it is blocked. <p>
 * Methods: <p>
 * - {@code getLeft()} returns the room to the left <p>
 * - {@code getRight()} returns the room to the right <p>
 * - {@code getUp()} returns the room above <p>
 * - {@code getDown()} returns the room below <p>
 * - {@code isBlocked()} returns if the corridor is blocked <p>
 * - {@code blockCorridor()} blocks the corridor if it was not already blocked <p>
 */
public class Corridor {
    private Room toLeft, toRight, toUp, toDown = null;
    private boolean isBlocked = false;

    Corridor(Room fromRoom, String fromDirection, Room toRoom) throws InvalidCorridorException, DuplicateCorridorException {
        switch (fromDirection) {
            case "left":
                this.toLeft = fromRoom;
                this.toRight = toRoom;
                this.toRight.setCorridorLeft(this);
                break;
            case "right":
                this.toRight = fromRoom;
                this.toLeft = toRoom;
                this.toLeft.setCorridorRight(this);
                break;
            case "up":
                this.toUp = fromRoom;
                this.toDown = toRoom;
                this.toDown.setCorridorUp(this);
                break;
            case "down":
                this.toDown = fromRoom;
                this.toUp = toRoom;
                this.toUp.setCorridorDown(this);
                break;
            default:
                throw new InvalidCorridorException();
        }
    }

    /**
     * Returns the room to the left, if there is one. <p>
     * Must be present if there is a room to the right.
     * @return the room to the left, if there is one
     */
    Room getLeft() {
        return this.toLeft;
    }

    /**
     * Returns the room to the right, if there is one. <p>
     * Must be present if there is a room to the left.
     * @return the room to the right, if there is one
     */
    Room getRight() {
        return this.toRight;
    }

    /**
     * Returns the room above, if there is one. <p>
     * Must be present if there is a room below.
     * @return the room above, if there is one
     */
    Room getUp() {
        return this.toUp;
    }

    /**
     * Returns the room below, if there is one. <p>
     * Must be present if there is a room above.
     * @return the room below, if there is one
     */
    Room getDown() {
        return this.toDown;
    }

    /**
     * Returns if the corridor is blocked.
     * @return if the corridor is blocked
     */
    boolean isBlocked() {
        return this.isBlocked;
    }

    /**
     * Blocks the corridor, if it is not already. <p>
     * If it was not blocked, block it and return true. <p>
     * If it was, do nothing and return false.
     * @return if the corridor is successfully blocked
     */
    boolean blockCorridor() {
        if (this.isBlocked) {
            return false;
        } else {
            this.isBlocked = true;
            return true;
        }
    }
}
