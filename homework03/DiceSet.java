
public class DiceSet {

    /**
     * private instance data
     */
    private int count;
    private int sides;
    private Die[] ds = null;

    // public constructor:
    /**
     * constructor
     * 
     * @param count int value containing total dice count
     * @param sides int value containing the number of pips on each die
     * @throws IllegalArgumentException if one or both arguments don't make sense
     * @note parameters are checked for validity; invalid values throw
     *       "IllegalArgumentException"
     */
    public DiceSet(int dieCount, int sidesOnDice) {
        if (dieCount < 2) {
            throw new IllegalArgumentException("   Too few die requested in constructor");

        }
        if (sidesOnDice < 4) {
            throw new IllegalArgumentException("   Too few sides requested in constructor");
        }
        sides = sidesOnDice;
        count = dieCount;

        this.ds = new Die[count];

        for (int i = 0; i < count; i++) {
            ds[i] = new Die(sides);

        }

    }

    /**
     * @return the sum of all the dice values in the set
     */
    public int sum() {
        int sumValue = 0;

        for (int i = 0; i < count; i++) {
            int resultAdd = ds[i].getValue();
            sumValue += resultAdd;
        }

        return sumValue;
    }

    /**
     * Randomly rolls all of the dice in this set NOTE: you will need to use one of
     * the "toString()" methods to obtain the values of the dice in the set
     */
    public void roll() {
        for (int i = 0; i < count; i++) {
            ds[i].roll();

        }
    }

    /**
     * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
     * 
     * @param dieIndex int of which die to roll
     * @return the integer value of the newly rolled die
     * @trhows IllegalArgumentException if the index is out of range
     */
    public int rollIndividual(int dieIndex) {

        ds[dieIndex].roll();

        return ds[dieIndex].getValue();

    }

    /**
     * Gets the value of the die in this set indexed by 'dieIndex'
     * 
     * @param dieIndex int of which die to roll
     * @trhows IllegalArgumentException if the index is out of range
     */
    public int getIndividual(int dieIndex) {

        return ds[dieIndex].getValue();
    }

    /**
     * @return Public Instance method that returns a String representation of the
     *         DiceSet instance
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < ds.length; i++) {
            result += "[" + ds[i].getValue() + "]";
        }
        return result;
    }

    /**
     * @return Class-wide version of the preceding instance method
     */
    public static String toString(DiceSet ds2) {
        String result = " ";
        for (int i = 0; i < ds2.count; i++) {
            String stringResult = Integer.toString(ds2.getIndividual(i));
            result += "[" + stringResult + "]";
        }

        return result;

    }

    /**
     * @return tru iff this set is identical to the set passed as an argument
     */
    public boolean isIdentical(DiceSet ds2) {
        boolean identical;
        if (ds.length == ds2.count && sides == ds2.sides) {
            identical = true;

        } else {
            identical = false;
        }
        return identical;

    }

    /**
     * A little test main to check things out
     */
    public static void main(String[] args) {

        System.out.println("TESTING THE DIE CLASS: TEST CONSTRUCTOR FOR INVALID NUMBERS OF SIDES:");
        Die d = null;
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

        System.out.println("TESTING THE DICESET CLASS: TEST CONSTRUCTOR FOR 3 SIX-SIDED DICE:");
        System.out.println("  DiceSet: 3 dice, six sided - ds36:");
        DiceSet ds36 = new DiceSet(3, 6);
        System.out.println("      initialized ds36 contents             : " + ds36.toString());
        ds36.roll();
        System.out.println("      after collective roll ds36 contents   : " + ds36.toString());
        ds36.rollIndividual(2);
        System.out.println("      after individual roll(2) ds36 contents: " + ds36.toString());
        System.out.println("      sum of dice values is: " + ds36.sum());
        System.out.println("   Re-rolling all dice");
        ds36.roll();
        System.out.println("      after collective roll ds36 contents   : " + ds36.toString());
        ds36.rollIndividual(2);
        System.out.println("      after individual roll(2) ds36 contents: " + ds36.toString());
        System.out.println("      sum of dice values is                 : " + ds36.sum());
        System.out.println("   Test of getIndividual(): ");
        System.out.println("      value of die at index 0: " + ds36.getIndividual(0));
        System.out.println("      value of die at index 1: " + ds36.getIndividual(1));
        System.out.println("      value of die at index 2: " + ds36.getIndividual(2));
    }
}