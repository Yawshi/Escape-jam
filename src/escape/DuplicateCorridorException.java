package escape;

public class DuplicateCorridorException extends Exception {
    DuplicateCorridorException() {
        super("Attempted to assign a corridor to a side of the room already with a corridor.");
    }
}
