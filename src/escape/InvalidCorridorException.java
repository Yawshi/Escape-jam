package escape;
/**
 * Thrown when constructing a {@code Corridor} object, arguments do not include strictly: <p>
 * - rooms to the left and right, or <p>
 * - rooms above and below. <p>
 * No other combinations are allowed.
 */
public class InvalidCorridorException extends Exception {
    /**
     * Thrown when constructing a {@code Corridor} object, arguments do not include strictly: <p>
     * - rooms to the left and right, or <p>
     * - rooms above and below. <p>
     * No other combinations are allowed.
     */
    InvalidCorridorException(){
        super("Corridor must have rooms either to left and right, or above and below.");
    }
}
