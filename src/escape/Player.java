package escape;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The protagonist of the game. You play as the player. Shocker. <p>
 * On their turn, the player can (and strictly must) either: <p>
 * > - move into an adjacent available room, if they have stamina remaining <p>
 * > - block the corridor to an adjacent available room, also replenishes stamina <p>
 * The player wins if they reach their goal room, before the Creature captures the player 
 * (by sharing a room with them at any point) or reaches its goal room (the player's start room). <p>
 * @author Yawshi
 */
public class Player {
    Room inRoom, startRoom, goalRoom;
    int stamina, step;

    /**
     * Initialises {@code Player} objects with their start and goal rooms 
     * and their starting stamina value (2) and step value (0), 
     * as well as places them in their start room.
     * @param start the player's start room
     * @param goal the player's goal room
     */
    Player(Room start, Room goal) {
        this.inRoom = start;
        this.startRoom = start;
        this.goalRoom = goal;
        this.stamina = 2;
        this.step = 0;
    }

    /**
     * Prints relevant information on the terminal, then 
     * prompts the player to make a decision for their turn. <p>
     * Executes these in order: <p>
     * 1. Prints the stamina amount of the player. This is how many more steps the player can 
     * take consecutively before having to take a break (spend a turn blocking). <p>
     * 2. Finds and makes a list of adjacent rooms the player can access from their current room.
     * They can either move into these rooms, or block the corridors to them. <p>
     * 3. If the player has no options, prints an "out of options" dialogue line and calls {@code promptDoNothing()} <p>
     * 4. Otherwise prints the player's options: <p>
     * > - First prints the player's move options. If they are out of stamina, 
     * instead prints an "out of stamina" dialogue line. <p>
     * > - Then prints the player's block options. This is always available if there is an available adjacent room. <p>
     * 5. Finally prompt the player to input an option: <p>
     * > - If {@code promptDoNothing()} was called, instead this input does nothing and no checks are applied. <p>
     * > - Otherwise, the input must strictly start with either "move " or "block ", followed by a valid direction
     * (left, right, up, down) that corresponds to an available option. <p>
     * > - For example, if printed was <p>
     * > > {@code move: left up} <p>
     * > > {@code block: left up} <p>
     * > > then only inputs "move left", "move up", "block left", "block up" are accepted. <p>
     * > - If an invalid input is received, inform the player and repeat this step until a valid input is recieved. <p>
     * > - If a valid input is received, execute the corresponding action.
     */
    void promptDecide() {
        System.out.println("Stamina: " + this.stamina);
        System.out.println("Choose and input your action: ");

        // finds available adjacent rooms
        ArrayList<String> adjacentRooms = new ArrayList<String>();
        if (stamina > 0) {
            if (this.inRoom.canGoLeft()) adjacentRooms.add("left");
            if (this.inRoom.canGoRight()) adjacentRooms.add("right");
            if (this.inRoom.canGoUp()) adjacentRooms.add("up");
            if (this.inRoom.canGoDown()) adjacentRooms.add("down");
        }

        // if there are no available adjacent rooms, inform the player
        if (adjacentRooms.isEmpty()) {
            System.out.println("Out of options! You trapped yourself in...");
            promptDoNothing();
            return;
        } else { // otherwise, print the player's options
            if (!adjacentRooms.isEmpty()) {
                System.out.print("move: ");
                for (String each : adjacentRooms) {
                    System.out.print(each + " ");
                }
                System.out.println();
            } else System.out.println("Out of stamina! Cannot move...");
            System.out.print("block: ");
            for (String each : adjacentRooms) {
                System.out.print(each + " ");
            }
            System.out.println();
        }

        // prompt and check player inputs, until a valid input is received, then execute action
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

    /**
     * Attempts to move to an adjacent room. <p>
     * If {@code direction} is a valid direction (left, right, up, down)
     * and the corresponding room is available to move into, move there and return true. 
     * (Moving involves decrementing stamina by 1, incrementing step by 1, moving this into the new room,  
     * and setting the new room's playerStep value to this current step value.)
     * Otherwise returns false.
     * @param direction the room to move into, relative to the room this is in
     * @return if a move is successfully made
     */
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

    /**
     * Attempts to block a corridor to an adjacent room. <p>
     * If {@code direction} is a valid direction (left, right, up, down) 
     * and the corresponding corridor is not blocked, block it and return true.
     * Otherwise return false.
     * @param direction the corridor to be blocked, relative to the room this is in
     * @return if the corridor is successfully blocked
     */
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

    /**
     * Print "do nothing" on the terminal (instead of the player's options this turn),
     * and prompt the player to enter a line (input content does nothing).
     */
    void promptDoNothing() {
        System.out.println("do nothing");

        @SuppressWarnings("resource")
            Scanner keyboard = new Scanner(System.in);
            keyboard.nextLine();
    }

    /**
     * Returns whether or not this player is at their goal.
     * @return whether or not this player is at their goal
     */
    boolean atGoal() {
        return (this.inRoom == this.goalRoom);
    }
}
