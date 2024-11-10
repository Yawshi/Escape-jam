package escape;

import java.util.Scanner;

public class Creature {
    Room inRoom, startRoom, goalRoom;

    Creature(Room start, Room goal) {
        this.inRoom = start;
        this.startRoom = start;
        this.goalRoom = goal;
    }

    void promptCreature() {
        System.out.println("Choose creature action: ");

        System.out.print("move: ");
        if (this.inRoom.canGoLeft()) System.out.print("left ");
        if (this.inRoom.canGoRight()) System.out.print("right ");
        if (this.inRoom.canGoUp()) System.out.print("up ");
        if (this.inRoom.canGoDown()) System.out.print("down ");
        System.out.println();

        boolean isValidInput = false;
        while (!isValidInput) {
            @SuppressWarnings("resource")
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();

            if ((input.startsWith("move "))) {
                isValidInput = move(input.substring(5));
            } else isValidInput = false;
            
            if (!isValidInput) {
                System.out.println("Invalid input, try again: ");
            }
        }
        System.out.println();
    }

    boolean move(String direction) {
        switch (direction) {
            case "left":
                if (this.inRoom.canGoLeft()) {
                    this.inRoom = this.inRoom.getRoomToLeft();
                    return true;
                } else return false;
            case "right":
                if (this.inRoom.canGoRight()) {
                    this.inRoom = this.inRoom.getRoomToRight();
                    return true;
                } else return false;
            case "up":
                if (this.inRoom.canGoUp()) {
                    this.inRoom = this.inRoom.getRoomToUp();
                    return true;
                } else return false;
            case "down":
                if (this.inRoom.canGoDown()) {
                    this.inRoom = this.inRoom.getRoomToDown();
                    return true;
                } else return false;
            default:
                return false;
        }
    }

    boolean atGoal() {
        return (this.inRoom == this.goalRoom);
    }
}
