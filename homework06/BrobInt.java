import java.util.Arrays;

public class BrobInt {

    public static final BrobInt ZERO = new BrobInt("0");

    public static final BrobInt ONE = new BrobInt("1");

    public static final BrobInt TEN = new BrobInt("10");

    public int sign;

    public int[] chunks;

    public BrobInt(String value) {
        // May want to create a method that chunks any extra digits. For instance, if
        // there is 00000045454, we want to get rid of the zeros.
        value = stringSignConversion(value);

        chunks = new int[value.length()];

        for (int i = 0; i < chunks.length; i++) {

            String d = String.valueOf(value.charAt(i));
            chunks[i] = Integer.parseInt(d);

        }

    }

    public String stringSignConversion(String value) {
        if (value.charAt(0) == '-') {
            sign = -1;

            return value.split("-")[1];

        } else if (value.equals("0")) {
            sign = 0;

            return value;

        } else {
            sign = 1;

            return value;

        }

    }

    // If A.add(B) A = object calling add. This = Value of original object.
    public BrobInt add(BrobInt value) {
        if (this.equals(ZERO)) {
            return value;
        }
        if (value.equals(ZERO)) {

            return this;
        }

        int smallSign = sign;
        int largeSign = value.sign;

        int[] smallChunks = chunks;
        int[] largeChunks = value.chunks;
        if (chunks.length >= value.chunks.length) {
            smallChunks = value.chunks;
            largeChunks = chunks;
            smallSign = value.sign;
            largeSign = sign;

        }
        int carry = 0;

        String answer = "";
        int[] result = new int[largeChunks.length + 1];
        if (largeSign == smallSign) {

            for (int i = 0; i < largeChunks.length; i++) {
                int sum = (smallChunks[smallChunks.length - i - 1]) + (largeChunks[largeChunks.length - i - 1]) + carry;

                carry = sum / 10;

                result[largeChunks.length - 1 - i] = 5;

            }

            answer = result.toString();

            BrobInt calculation = new BrobInt(answer);

            return calculation;

            /*
             * if (largeSign < 0 && smallSign < 0) { String negSign = "-";
             * 
             * answer = negSign + answer;
             * 
             * BrobInt calculation = new BrobInt(answer);
             * 
             * return calculation;
             * 
             * } else {
             * 
             * BrobInt calculation = new BrobInt(answer);
             * 
             * return calculation; }
             */

        } else {

            this.subtract(value);

            return this;
        }

    }

    public BrobInt subtract(BrobInt value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public BrobInt multiply(BrobInt value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public BrobInt divide(BrobInt value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public BrobInt remainder(BrobInt value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public String toString() {

        String n = sign >= 0 ? "" : "-";

        for (int i = 0; i < chunks.length; i++) {
            n += String.valueOf(chunks[i]);
        }

        return n;

    }

    public int compareTo(BrobInt value) {

        return 0;

    }

    public boolean equals(Object x) {

        return true;

    }

    public static BrobInt valueOf(long value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public static void main(String[] arguments) {

        BrobInt box1 = new BrobInt(arguments[0]);

        BrobInt box2 = new BrobInt(arguments[1]);

        BrobInt box3 = box1.add(box2);

        System.out.println("Your answer is: " + box3);

    }
}