package escape;

import java.util.Scanner;

public class Player {
    Room inRoom, previousRoom, startRoom, goalRoom;
    int stamina;

    Player(Room start, Room goal) {
        this.inRoom = start;
        this.previousRoom = start;
        this.startRoom = start;
        this.goalRoom = goal;
        this.stamina = 2;
    }

    void promptPlayer(){
        System.out.println("Stamina: " + this.stamina);
        System.out.println("Choose and input your action: ");

        if (stamina > 0) {
            System.out.print("move: ");
            if (this.inRoom.canGoLeft()) System.out.print("left ");
            if (this.inRoom.canGoRight()) System.out.print("right ");
            if (this.inRoom.canGoUp()) System.out.print("up ");
            if (this.inRoom.canGoDown()) System.out.print("down ");
            System.out.println();
        } else System.out.println("Cannot move! Out of stamina");

        System.out.print("block: ");
        if (this.inRoom.canGoLeft()) System.out.print("left ");
        if (this.inRoom.canGoRight()) System.out.print("right ");
        if (this.inRoom.canGoUp()) System.out.print("up ");
        if (this.inRoom.canGoDown()) System.out.print("down ");
        System.out.println();

        System.out.println("do nothing");

        boolean isValidInput = false;
        while (!isValidInput) {
            @SuppressWarnings("resource")
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();

            if ((stamina > 0) && (input.startsWith("move "))) {
                isValidInput = move(input.substring(5));
            } else if (input.startsWith("block ")) {
                isValidInput = blockCorridor(input.substring(6));
            } else if (input.equals("do nothing")) {
                isValidInput = true;
                stamina = 2;
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
                    this.previousRoom = this.inRoom;
                    this.inRoom = this.inRoom.getRoomToLeft();
                    stamina -= 1;
                    return true;
                } else return false;
            case "right":
                if (this.inRoom.canGoRight()) {
                    this.previousRoom = this.inRoom;
                    this.inRoom = this.inRoom.getRoomToRight();
                    stamina -= 1;
                    return true;
                } else return false;
            case "up":
                if (this.inRoom.canGoUp()) {
                    this.previousRoom = this.inRoom;
                    this.inRoom = this.inRoom.getRoomToUp();
                    stamina -= 1;
                    return true;
                } else return false;
            case "down":
                if (this.inRoom.canGoDown()) {
                    this.previousRoom = this.inRoom;
                    this.inRoom = this.inRoom.getRoomToDown();
                    stamina -= 1;
                    return true;
                } else return false;
            default:
                return false;
        }
    }

    boolean blockCorridor(String direction) {
        switch (direction) {
            case "left":
                if (this.inRoom.blockCorridorLeft()) {
                    stamina = 2;
                    return true;
                } else return false;
            case "right":
                if (this.inRoom.blockCorridorRight()) {
                    stamina = 2;
                    return true;
                } else return false;
            case "up":
                if (this.inRoom.blockCorridorUp()) {
                    stamina = 2;
                    return true;
                } else return false;
            case "down":
                if (this.inRoom.blockCorridorDown()) {
                    stamina = 2;
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
