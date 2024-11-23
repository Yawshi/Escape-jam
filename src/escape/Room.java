package escape;

public class Room {
    private Corridor corridorLeft, corridorRight, corridorUp, corridorDown;
    private int playerStep = 0;

    Room() {
    }

    Room(Room toLeft, Room toRight, Room toUp, Room toDown) throws InvalidCorridorException, DuplicateCorridorException {
        if (toLeft instanceof Room) setCorridorLeft(new Corridor(this, "right", toLeft));
        if (toRight instanceof Room) setCorridorRight(new Corridor(this, "left", toRight));
        if (toUp instanceof Room) setCorridorUp(new Corridor(this, "down", toUp));
        if (toDown instanceof Room) setCorridorDown(new Corridor(this, "up", toDown));
    }

    Room getRoomToLeft() {
        return this.corridorLeft.getLeft();
    }

    boolean canGoLeft() {
        if (corridorLeft == null) return false;
        return !(corridorLeft.isBlocked());
    }

    void setCorridorLeft(Corridor toLeft) throws InvalidCorridorException, DuplicateCorridorException {
        if (this.corridorLeft == null) {
            this.corridorLeft = toLeft;
        } else throw new DuplicateCorridorException();
    }

    boolean blockCorridorLeft() {
        if (this.canGoLeft()) {
            return this.corridorLeft.blockCorridor();
        } else return false;
    }

    Room getRoomToRight() {
        return this.corridorRight.getRight();
    }

    boolean canGoRight() {
        if (corridorRight == null) return false;
        return !(corridorRight.isBlocked());
    }

    void setCorridorRight(Corridor toRight) throws InvalidCorridorException, DuplicateCorridorException {
        if (this.corridorRight == null) {
            this.corridorRight = toRight;
        } else throw new DuplicateCorridorException();
    }

    boolean blockCorridorRight() {
        if (this.canGoRight()) {
            return this.corridorRight.blockCorridor();
        } else return false;
    }

    Room getRoomToUp() {
        return this.corridorUp.getUp();
    }

    boolean canGoUp() {
        if (corridorUp == null) return false;
        return !(corridorUp.isBlocked());
    }

    void setCorridorUp(Corridor toUp) throws InvalidCorridorException, DuplicateCorridorException {
        if (this.corridorUp == null) {
            this.corridorUp = toUp;
        } else throw new DuplicateCorridorException();
    }

    boolean blockCorridorUp() {
        if (this.canGoUp()) {
            return this.corridorUp.blockCorridor();
        } else return false;
    }

    Room getRoomToDown() {
        return this.corridorDown.getDown();
    }

    boolean canGoDown() {
        if (corridorDown == null) return false;
        return !(corridorDown.isBlocked());
    }

    void setCorridorDown(Corridor toDown) throws InvalidCorridorException, DuplicateCorridorException {
        if (this.corridorDown == null) {
            this.corridorDown = toDown;
        } else throw new DuplicateCorridorException();
    }

    boolean blockCorridorDown() {
        if (this.canGoDown()) {
            return this.corridorDown.blockCorridor();
        } else return false;
    }

    int getPlayerStepHere() {
        return this.playerStep;
    }

    void setPlayerStepHere(int step) {
        this.playerStep = step;
    }
}
