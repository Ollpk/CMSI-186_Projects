import java.util.concurrent.ThreadLocalRandom;

public class Die {

    /**
     * private instance data
     */
    private int sides;
    private int value;
    private final int MINIMUM_SIDES = 4;

    // public constructor:
    /**
     * constructor
     * 
     * @param nSides int value containing the number of sides to build on THIS Die
     * @throws IllegalArgumentException Note: parameter must be checked for
     *                                  validity; invalid value must throw
     *                                  "IllegalArgumentException"
     */
    public Die(int nSides) { // random number generated between amount of sides .

        if (nSides < MINIMUM_SIDES) {
            throw new IllegalArgumentException("Too few sides requested to constructor....");
        }

        this.sides = nSides;
        this.value = ThreadLocalRandom.current().nextInt(MINIMUM_SIDES, sides + 1);

    }

    /**
     * Roll THIS die and return the result
     * 
     * @return integer value of the result of the roll, randomly selected
     */
    public int roll() {

        value = ThreadLocalRandom.current().nextInt(MINIMUM_SIDES, sides + 1);

        return value;
    }

    /**
     * Get the value of THIS die to return to the caller; note that the way the
     * count is determined is left as a design decision to the programmer For
     * example, what about a four-sided die - which face is considered its "value"?
     * 
     * @return the pip count of THIS die instance
     */
    public int getValue() {

        return value;
    }

    /**
     * Public Instance method that returns a String representation of THIS die
     * instance
     * 
     * @return String representation of this Die
     */
    public String toString() {
        String diceValue = Integer.toString(value);

        return "[" + diceValue + "]";
    }

    /**
     * Class-wide method that returns a String representation of THIS die instance
     * 
     * @return String representation of this Die
     */
    public static String toString(Die d) {
        String diceValue = Integer.toString(d.value);
        return diceValue;
    }

    /**
     * A little test main to check things out
     */
    public static void main(String[] args) {
        System.out.println("Hello world from the Die class...");
        Die d = null;
        System.out.println("TESTING THE DIE CLASS: TEST CONSTRUCTOR FOR INVALID NUMBERS OF SIDES:");

        try {
            d = new Die(-1);
        } catch (IllegalArgumentException iae) {
            System.out.println("   Too few sides requested to constructor....");
        }
        try {
            d = new Die(1);
        } catch (IllegalArgumentException iae) {
            System.out.println("   Too few sides requested to constructor....");
        }
        try {
            d = new Die(2);
        } catch (IllegalArgumentException iae) {
            System.out.println("   Too few sides requested to constructor....");
        }
        try {
            d = new Die(3);
        } catch (IllegalArgumentException iae) {
            System.out.println("   Too few sides requested to constructor....");
        }
        System.out.println("TESTING THE DIE CLASS: TESTS ON 8-SIDED DIE:");
        try {
            d = new Die(8);
        } catch (IllegalArgumentException iae) {
            System.out.println("Too few sides requested to constructor....");
        }
        System.out.println("   roll() test for 8 sided die: ");
        System.out.println("   You rolled a " + d.roll());
        System.out.println("   You rolled a " + d.roll());
        System.out.println("   You rolled a " + d.roll());
        System.out.println("   You rolled a " + d.roll());
        System.out.println("   You rolled a " + d.roll());
        System.out.println("   Current value is: " + d.toString());

    }

}