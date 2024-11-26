package escape;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    Room inRoom, startRoom, goalRoom;
    int stamina, step;

    Player(Room start, Room goal) {
        this.inRoom = start;
        this.startRoom = start;
        this.goalRoom = goal;
        this.stamina = 2;
        this.step = 0;
    }

    void promptDecide() {
        System.out.println("Stamina: " + this.stamina);
        System.out.println("Choose and input your action: ");

        ArrayList<String> moveOptions = new ArrayList<String>();
        if (stamina > 0) {
            if (this.inRoom.canGoLeft()) moveOptions.add("left");
            if (this.inRoom.canGoRight()) moveOptions.add("right");
            if (this.inRoom.canGoUp()) moveOptions.add("up");
            if (this.inRoom.canGoDown()) moveOptions.add("down");
        }

        ArrayList<String> blockOptions = new ArrayList<String>();
        if (this.inRoom.canGoLeft()) blockOptions.add("left");
        if (this.inRoom.canGoRight()) blockOptions.add("right");
        if (this.inRoom.canGoUp()) blockOptions.add("up");
        if (this.inRoom.canGoDown()) blockOptions.add("down");

        if (blockOptions.isEmpty()) {
            System.out.println("Out of options! You trapped yourself in...");
            return;
        } else {
            if (!moveOptions.isEmpty()) {
                System.out.print("move: ");
                for (String each : moveOptions) {
                    System.out.print(each + " ");
                }
                System.out.println();
            } else System.out.println("Out of stamina! Cannot move...");
            System.out.print("block: ");
            for (String each : blockOptions) {
                System.out.print(each + " ");
            }
            System.out.println();
        }

        boolean isValidInput = false;
        while (!isValidInput) {
            @SuppressWarnings("resource")
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();

            if ((stamina > 0) && (input.startsWith("move "))) {
                isValidInput = move(input.substring(5));
            } else if (input.startsWith("block ")) {
                isValidInput = blockCorridor(input.substring(6));
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
                    stamina -= 1;
                    step += 1;
                    this.inRoom.setPlayerStepHere(step);
                    return true;
                } else return false;
            case "right":
                if (this.inRoom.canGoRight()) {
                    this.inRoom = this.inRoom.getRoomToRight();
                    stamina -= 1;
                    step += 1;
                    this.inRoom.setPlayerStepHere(step);
                    return true;
                } else return false;
            case "up":
                if (this.inRoom.canGoUp()) {
                    this.inRoom = this.inRoom.getRoomToUp();
                    stamina -= 1;
                    step += 1;
                    this.inRoom.setPlayerStepHere(step);
                    return true;
                } else return false;
            case "down":
                if (this.inRoom.canGoDown()) {
                    this.inRoom = this.inRoom.getRoomToDown();
                    stamina -= 1;
                    step += 1;
                    this.inRoom.setPlayerStepHere(step);
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
