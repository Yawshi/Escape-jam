package escape;

import java.util.ArrayList;

public class Creature {
    Room inRoom, previousRoom, startRoom, goalRoom;
    int highestPlayerStepFound = 0;

    Creature(Room start, Room goal) {
        this.inRoom = start;
        this.previousRoom = start;
        this.startRoom = start;
        this.goalRoom = goal;
    }

    void decideMove(Player player) {
        Room moveTo = this.inRoom;

        // checks which rooms it can move to, and makes a list of them
        ArrayList<Room> adjacentRooms = new ArrayList<Room>();
        if (this.inRoom.canGoDown()) {
            adjacentRooms.add(this.inRoom.getRoomToDown());
        }
        if (this.inRoom.canGoRight()) {
            adjacentRooms.add(this.inRoom.getRoomToRight());
        }
        if (this.inRoom.canGoUp()) {
            adjacentRooms.add(this.inRoom.getRoomToUp());
        }
        if (this.inRoom.canGoLeft()) {
            adjacentRooms.add(this.inRoom.getRoomToLeft());
        }

        // if there is only one option, it was the previous room. move there
        if (adjacentRooms.size() == 1) {
            moveTo = this.previousRoom;
            this.previousRoom = this.inRoom;
            this.inRoom = moveTo;
            return;
        }

        // do not go back to the previous room
        adjacentRooms.remove(previousRoom);
        this.previousRoom = this.inRoom;

        // if there is only one option left now, move there
        if (adjacentRooms.size() == 1) {
            this.inRoom = adjacentRooms.get(0);
            return;
        }

        // check remaining options for playerSteps, if found go to highest found
        // if player is in any of the options, it always has the highest playerStep
        // note: the highest playerStep found is recorded, and any found in the future that is 
        //      lower than this will be ignored
        for (Room room : adjacentRooms) {
            if (room.getPlayerStepHere() > this.highestPlayerStepFound) {
                this.highestPlayerStepFound = room.getPlayerStepHere();
                moveTo = room;
            }
        }
        if (moveTo != this.inRoom) {
            this.inRoom = moveTo;
            return;
        }

        // check remaining options for its goal, if found go there
        if (adjacentRooms.contains(this.goalRoom)) {
            this.inRoom = this.goalRoom;
            return;
        }

        // if going down is one of the remaining options, go down
        // else pick a random remaining option
        if (this.inRoom.canGoDown() && adjacentRooms.contains(this.inRoom.getRoomToDown())) {
            this.inRoom = this.inRoom.getRoomToDown();
        } else {
            this.inRoom = adjacentRooms.get((int) Math.floor(Math.random() * adjacentRooms.size()));
        }
    }

    boolean atGoal() {
        return (this.inRoom == this.goalRoom);
    }
}
