package escape;

public class Game {
    Room[] roomPointer = new Room[9];
    Player player;
    Creature creature;

    public Game() throws InvalidCorridorException, DuplicateCorridorException {
        layoutRooms3x3();
        player = new Player(roomPointer[7], roomPointer[1]);
        printLayout3x3(false);
        player.promptDecide();
        creature = new Creature(roomPointer[1], roomPointer[7]);
        boolean isCreatureVisible = true;
        while (true) {
            printLayout3x3(isCreatureVisible);

            player.promptDecide();
            if (player.inRoom == creature.inRoom) {
                printLayout3x3(true);
                System.out.println("The creature caught up to you. YOU LOSE");
                break;
            }
            if (player.atGoal()) {
                printLayout3x3(true);
                System.out.println("You reach the goal! YOU WIN!");
                break;
            }
            isCreatureVisible = true;

            creature.decideMove(player);
            if (player.inRoom == creature.inRoom) {
                printLayout3x3(true);
                System.out.println("The creature caught up to you. YOU LOSE");
                break;
            }
            if (creature.atGoal()) {
                printLayout3x3(true);
                System.out.println("The creature reached its goal. YOU LOSE");
                break;
            }
        }
    }

    void layoutRooms3x3() throws InvalidCorridorException, DuplicateCorridorException {
        roomPointer[1] = new Room();
        roomPointer[0] = new Room(null, roomPointer[1], null, null);
        roomPointer[2] = new Room(roomPointer[1], null, null, null);

        roomPointer[4] = new Room(null, null, roomPointer[1], null);
        roomPointer[3] = new Room(null, roomPointer[4], roomPointer[0], null);
        roomPointer[5] = new Room(roomPointer[4], null, roomPointer[2], null);

        roomPointer[7] = new Room(null, null, roomPointer[4], null);
        roomPointer[6] = new Room(null, roomPointer[7], roomPointer[3], null);
        roomPointer[8] = new Room(roomPointer[7], null, roomPointer[5], null);
    }

    void printLayout3x3(boolean isCreatureVisible) {
        System.out.println("+-----+-----+-----+");
        System.out.println("|     |     |     |");
        printRoomRow3x3(0, isCreatureVisible);
        System.out.println("|     |     |     |");
        printWallRow3x3(0);
        System.out.println("|     |     |     |");
        printRoomRow3x3(1, isCreatureVisible);
        System.out.println("|     |     |     |");
        printWallRow3x3(1);
        System.out.println("|     |     |     |");
        printRoomRow3x3(2, isCreatureVisible);
        System.out.println("|     |     |     |");
        System.out.println("+-----+-----+-----+");
    }

    void printRoomRow3x3(int roomRow, boolean isCreatureVisible) {
        System.out.print(  "|  ");
        printRoomContents(roomPointer[roomRow*3], isCreatureVisible);
        System.out.print(      "  ");
        printCorridor(roomPointer[roomRow*3], true);
        System.out.print(         "  ");
        printRoomContents(roomPointer[roomRow*3+1], isCreatureVisible);
        System.out.print(            "  ");
        printCorridor(roomPointer[roomRow*3+1], true);
        System.out.print(               "  ");
        printRoomContents(roomPointer[roomRow*3+2], isCreatureVisible);
        System.out.print(                  "  |");
        System.out.println();
    }

    void printWallRow3x3(int wallRow) {
        System.out.print(  "+--");
        printCorridor(roomPointer[wallRow*3], false);
        System.out.print(      "--+--");
        printCorridor(roomPointer[wallRow*3+1], false);
        System.out.print(            "--+--");
        printCorridor(roomPointer[wallRow*3+2], false);
        System.out.print(                  "--+");
        System.out.println();
    }

    void printRoomContents(Room checkRoom, boolean isCreatureVisible) {
        if ((creature instanceof Creature) && (isCreatureVisible) && (checkRoom == creature.inRoom)) {
            System.out.print('C');
        } else if (checkRoom == player.inRoom) {
            System.out.print('P');
        } else if (checkRoom == player.startRoom) {
            System.out.print('S');
        } else if (checkRoom == player.goalRoom) {
            System.out.print('E');
        } else System.out.print(' ');
    }

    void printCorridor(Room fromRoom, boolean isHorizontalCorridor) {
        char output = ' ';
        if (isHorizontalCorridor && !fromRoom.canGoRight()) output = 'X';
        if (!isHorizontalCorridor && !fromRoom.canGoDown()) output = 'X';
        System.out.print(output);
    }
}
