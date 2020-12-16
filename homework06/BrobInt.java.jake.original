import java.math.BigInteger;
import java.util.Arrays;

public class BrobInt {

    public static final BrobInt ZERO = new BrobInt("0");

    public static final BrobInt ONE = new BrobInt("1");

    public static final BrobInt TEN = new BrobInt("10");

    public static final BrobInt negOne = new BrobInt("-1");

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
        System.out.println("\n   input numbers to add: this: " + this.toString() + " and value: " + value.toString());
        if (this.equals(BrobInt.ZERO)) {
            return value;
        }
        if (value.equals(BrobInt.ZERO)) {

            return this;
        }

        int sign1 = this.compareTo(value);
        int smallSign = sign;
        int largeSign = value.sign;
        String answer = "";
        int[] smallChunks = chunks;
        int[] largeChunks = value.chunks;
        int carry = 0;
        if (chunks.length >= value.chunks.length) {
            smallChunks = value.chunks;
            largeChunks = chunks;
            smallSign = value.sign;
            largeSign = sign;
        }
        // if both numbers are the Same number!
        if (sign1 == 0) {
            if (value.sign == -1) {
                String str = value.toString();
                str.replace("-", "");
                BrobInt bint = new BrobInt(str);
                this.subtract(bint);

            } else {
                int[] results = new int[largeChunks.length];
                for (int i = 0; i < largeChunks.length; i++) {
                    int sum = ((this.chunks[this.chunks.length - 1 - i] + value.chunks[value.chunks.length - 1 - i])
                            + carry);
                    carry = sum / 10;
                    if (this.chunks.length - 1 - i == 0 && value.chunks.length - 1 - i == 0) {
                        results[largeChunks.length - 1 - i] = sum;
                    } else {
                        results[largeChunks.length - 1 - i] = sum % 10;
                    }

                }

            }
            // when the first number is Larger than the Second number!
        } else if (sign1 == 1) {
            if (value.sign == -1) {
                String str = value.toString();
                str = str.replace("-", "");
                BrobInt bint = new BrobInt(str);
                this.subtract(bint);

            } else {
                int[] results = new int[largeChunks.length];
                for (int i = 0; i < largeChunks.length; i++) {
                    if (smallChunks.length - 1 - i > -1) {
                        int sum = (smallChunks[smallChunks.length - i - 1]) + (largeChunks[largeChunks.length - i - 1])
                                + carry;
                        carry = sum / 10;
                        if (this.chunks.length - 1 - i == 0 && value.chunks.length - 1 - i == 0) {
                            results[largeChunks.length - 1 - i] = sum;
                        } else {
                            results[largeChunks.length - 1 - i] = sum % 10;
                        }

                    } else {
                        results[largeChunks.length - 1 - i] = largeChunks[largeChunks.length - i - 1];

                    }
                }
                for (int i = 0; i < results.length - 1; i++) {
                    answer += Integer.toString(results[i]);
                }
                System.out.println("    Answer is: " + answer);

                BrobInt calculation = new BrobInt(answer);

                return calculation;

            }
            // When the first number is SMALLER than the second number
        } else {
            if (value.sign == -1) {
                String str = value.toString();
                str.replace("-", "");
                BrobInt bint = new BrobInt(str);
                this.subtract(bint);

            } else {
                int[] results = new int[largeChunks.length];
                for (int i = 0; i < largeChunks.length; i++) {
                    if (smallChunks.length - 1 - i > -1) {
                        int sum = (smallChunks[smallChunks.length - i - 1]) + (largeChunks[largeChunks.length - i - 1])
                                + carry;
                        carry = sum / 10;
                        if (this.chunks.length - 1 - i == 0 && value.chunks.length - 1 - i == 0) {
                            results[largeChunks.length - 1 - i] = sum;
                        } else {
                            results[largeChunks.length - 1 - i] = sum % 10;
                        }

                    }
                    results[largeChunks.length - 1 - i] = largeChunks[largeChunks.length - i - 1];

                }
                for (int i = 0; i < results.length - 1; i++) {
                    answer += Integer.toString(results[i]);
                }
                System.out.println("    Answer is: " + answer);

                BrobInt calculation = new BrobInt(answer);

                return calculation;
            }

        }
        return BrobInt.ONE;

    }

    public void toArray(int[] d) {
        System.out.println("Array contents: " + Arrays.toString(d));

    }

    public BrobInt subtract(BrobInt bint) {
        // throw new UnsupportedOperationException("\n Sorry, that operation is not yet
        // implemented.");

        // remember - this is the method that will tell you which number is bigger. 0
        // tells you that they are the same number, -1 if the first one is smaller, and
        // 1 if the first one is bigger
        String finalAnswer = "";
        int sign2 = this.compareTo(bint);
        System.out.println("This is your sign that is provided from compare method when subtracting " + this.toString()
                + " - " + bint.toString() + ":  " + sign2);
        System.out.println("This is the sign of your second value: " + bint.sign);
        // if both of the numbers are the same number!
        if (sign2 == 0) {
            return ZERO;

            // if the first number is smaller than the second number!
        } else if (sign2 == -1) {

            if (bint.sign == -1) {

                BrobInt answer = this.add(new BrobInt(bint.toString().substring(1)));
                System.out.println("Adding: Answer = " + answer);
                return answer;
            } else {

                int[] results = new int[bint.chunks.length];
                System.out.println("This is the length of your array: " + results.length);
                toArray(results);
                for (int i = 0; i < bint.chunks.length; i++) {

                    // Need to separate conditions for when the index of the smaller number is ran
                    // up. Can't have a negative -1 index...

                    if (this.chunks.length - 1 - i > -1) {

                        if (bint.chunks[bint.chunks.length - 1 - i] >= this.chunks[this.chunks.length - 1 - i]) {
                            int calculation1 = bint.chunks[bint.chunks.length - 1 - i]
                                    - this.chunks[this.chunks.length - 1 - i];
                            results[results.length - 1 - i] = calculation1;

                        } else {
                            bint.chunks[bint.chunks.length - 1 - i] += 10;
                            int calculation2 = bint.chunks[bint.chunks.length - 1 - i]
                                    - this.chunks[this.chunks.length - 1 - i];
                            results[results.length - 1 - i] = calculation2;
                            if (bint.chunks[bint.chunks.length - 2 - i] > 0) {
                                bint.chunks[bint.chunks.length - 2 - i] -= 1;
                            } else {
                                bint.chunks[bint.chunks.length - 2 - i] += 9;
                                bint.chunks[bint.chunks.length - 3 - i] -= 1;
                            }

                        }

                    } else {
                        if (bint.chunks[bint.chunks.length - 1 - i] > 0) {
                            results[results.length - 1 - i] = bint.chunks[bint.chunks.length - 1 - i];
                        } else if (bint.chunks[bint.chunks.length - 1 - i] < 0) {
                            bint.chunks[bint.chunks.length - 1 - i] += 10;
                            results[results.length - 1 - i] = bint.chunks[bint.chunks.length - 1 - i];
                            bint.chunks[bint.chunks.length - 2 - i] -= 1;

                        }

                    }
                }

                for (int i = 0; i < results.length; i++) {
                    finalAnswer += Integer.toString(results[i]);
                }
                System.out.println("    Answer is: " + finalAnswer);

                BrobInt calculation = new BrobInt(finalAnswer);

                BrobInt answer = removeLeadingZeros(calculation);

                return answer;

            }
            // For if the first number is BIGGER than the Second number
        } else if (sign2 == 1) {

            if (bint.sign == -1) {
                String str = bint.toString();
                str.replace("-", "");
                BrobInt value = new BrobInt(str);
                BrobInt answer = this.add(value);
                return answer;
            } else {

                int[] results = new int[this.chunks.length];
                for (int i = 0; i < this.chunks.length; i++) {
                    if (bint.chunks.length - 1 - i > -1) {
                        if (this.chunks[this.chunks.length - 1 - i] >= bint.chunks[bint.chunks.length - 1 - i]) {
                            int calculation1 = this.chunks[this.chunks.length - 1 - i]
                                    - bint.chunks[bint.chunks.length - 1 - i];
                            results[results.length - 1 - i] = calculation1;

                        } else {
                            this.chunks[this.chunks.length - 1 - i] += 10;
                            int calculation2 = this.chunks[this.chunks.length - 1 - i]
                                    - bint.chunks[bint.chunks.length - 1 - i];
                            results[results.length - 1 - i] = calculation2;
                            this.chunks[this.chunks.length - 2 - i] -= 1;

                        }
                    } else {
                        results[results.length - 1 - i] = this.chunks[this.chunks.length - 1 - i];
                    }
                }

                for (int i = 0; i < results.length; i++) {
                    finalAnswer += Integer.toString(results[i]);
                }
                System.out.println("    Answer is: " + finalAnswer);

                return removeLeadingZeros(new BrobInt(finalAnswer));
            }

        } else {
            throw new IllegalArgumentException("cannot perform calculation of " + this + "  -  " + bint);
        }

    }

    public int compareTo(BrobInt bint) {

        // remove any leading zeros because we will compare lengths
        String me = removeLeadingZeros(this).toString();
        String arg = removeLeadingZeros(bint).toString();

        // check if they are equal first, and return a zero if so
        if (this.equals(bint)) {
            return 0;
        }

        // handle the signs here
        // if "this" is neg and "bint" is pos, "this" is smaller so return -1
        if (-1 == sign && 1 == bint.sign) {
            return -1;

            // if "this" is pos and "bint" is neg, "this" is larger so return +1
        } else if (1 == sign && -1 == bint.sign) {
            return 1;
        }

        // otherwise, signs are the same, so we must check the lengths
        // the longer one is going to be the MORE OF THAT SIGN
        // e.g., "-1111" for "this" is more neg than "-222" for "arg" so return -1
        if ((-1 == sign) && (-1 == bint.sign)) {
            if (me.length() < arg.length()) {
                return 1;
            } else if (me.length() > arg.length()) {
                return -1;
            }
        } else if ((1 == sign) && (1 == bint.sign)) {
            if (me.length() < arg.length()) {
                return -1;
            } else if (me.length() > arg.length()) {
                return 1;
            }
        }

        // compare digit-by-digit
        // can only go to the length of the shortest if they are different lengths
        // int end = (me.length() < arg.length()) ? me.length() : arg.length();
        for (int i = 0; i < me.length(); i++) {
            if (me.charAt(i) < arg.charAt(i)) {
                return -1;
            } else if (me.charAt(i) > arg.charAt(i)) {
                return 1;
            }
        }
        return 0; // if it gets here, just assume equality to fool the compiler
    }

    public BrobInt removeLeadingZeros(BrobInt bint) {
        Character sign = null;
        String returnString = bint.toString();
        int index = 0;

        if (allZeroDetect(bint)) {
            return bint;
        }
        if (('-' == returnString.charAt(index)) || ('+' == returnString.charAt(index))) {
            sign = returnString.charAt(index);
            index++;
        }
        if (returnString.charAt(index) != '0') {
            return bint;
        }

        while (returnString.charAt(index) == '0') {
            index++;
        }
        returnString = bint.toString().substring(index, bint.toString().length());
        if (sign != null) {
            returnString = sign.toString() + returnString;
        }
        return new BrobInt(returnString);

    }

    public boolean allZeroDetect(BrobInt bint) {
        for (int i = 0; i < bint.toString().length(); i++) {
            if (bint.toString().charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    /*
     * public BrobInt subtract(BrobInt value) {
     * 
     * System.out.println("\n   input numbers to add: this: " + this.toString() +
     * " and value: " + value.toString()); // checks whether each term is a 0 and
     * how that affects subtraction based on // positive/negative #. if
     * (this.equals(BrobInt.ZERO)) { if (value.sign < 0) { this.add(value); return
     * this; } else { value.multiply(negOne); return value; } } if
     * (value.equals(BrobInt.ZERO)) {
     * 
     * return this; } // Checks if second term to be subtracted is a negative # if
     * (value.sign < 0) { this.add(value); return this; } // Checks if you subtract
     * a number by itself if (value.equals(this)) { return ZERO; }
     * 
     * int carry = 0; String answer = "";
     * 
     * // Checks if first number to be subtracted from is a negative number
     * 
     * if (this.sign < 0) { BrobInt calculation1 = value.multiply(negOne);
     * 
     * calculation1 = this.add(calculation1);
     * 
     * return calculation1; }
     * 
     * // Subtraction between two positive numbers based on length
     * 
     * // if the first number is larger than the second (in length) if
     * (this.chunks.length > value.chunks.length) { int[] results = new
     * int[this.chunks.length - 1]; for (int i = 0; i < this.chunks.length - 1; i++)
     * {
     * 
     * if (value.chunks.length - 1 - i > -1) {
     * 
     * if (this.chunks[this.chunks.length - 1 - i] >=
     * value.chunks[value.chunks.length - 1 - i]) { int calculation =
     * this.chunks[this.chunks.length - 1 - i] - value.chunks[value.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation; } else {
     * this.chunks[this.chunks.length - 1 - i] += 10;
     * 
     * int calculation2 = this.chunks[this.chunks.length - 1 - i] -
     * value.chunks[value.chunks.length - 1 - i]; results[results.length - 1 - i] =
     * calculation2; this.chunks[this.chunks.length - 2 - i] -= 1;
     * 
     * }
     * 
     * } else {
     * 
     * results[results.length - 1 - i] = this.chunks[this.chunks.length - 1 - i];
     * 
     * }
     * 
     * } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * } System.out.println("    Answer is: " + answer);
     * 
     * BrobInt calculation = new BrobInt(answer);
     * 
     * BrobInt finalAnswer = calculation.multiply(negOne);
     * 
     * return calculation;
     * 
     * } // if the second number is larger than the first number (in length) else if
     * (this.chunks.length < value.chunks.length) { int[] results = new
     * int[value.chunks.length - 1]; for (int i = 0; i < value.chunks.length - 1;
     * i++) { if (this.chunks.length - 1 - i > -1) { if
     * (value.chunks[this.chunks.length - 1 - i] >= this.chunks[value.chunks.length
     * - 1 - i]) { int calculation3 = value.chunks[value.chunks.length - 1 - i] -
     * this.chunks[this.chunks.length - 1 - i]; results[results.length - 1 - i] =
     * calculation3;
     * 
     * } else { value.chunks[value.chunks.length - 1 - i] += 10; int calculation4 =
     * value.chunks[value.chunks.length - 1 - i] - this.chunks[this.chunks.length -
     * 1 - i]; value.chunks[this.chunks.length - 2 - i] -= 1; } } else {
     * results[results.length - 1 - i] = value.chunks[value.chunks.length - 1 - i];
     * }
     * 
     * } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * } System.out.println("    Answer is: -" + answer);
     * 
     * BrobInt calculation = new BrobInt(answer);
     * 
     * BrobInt finalAnswer = calculation.multiply(negOne);
     * 
     * return finalAnswer;
     * 
     * } // For numbers of same length else if (this.chunks.length ==
     * value.chunks.length) { int[] results = new int[this.chunks.length - 1]; // if
     * the first number is larger than the second one if (this.chunks[0] >
     * value.chunks[0]) { for (int i = 0; i < value.chunks.length - 1; i++) {
     * 
     * if (this.chunks[this.chunks.length - 1 - i] >=
     * value.chunks[value.chunks.length - 1 - i]) { int calculation =
     * this.chunks[this.chunks.length - 1 - i] - value.chunks[value.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation; } else {
     * this.chunks[this.chunks.length - 1 - i] += 10; int calculation2 =
     * this.chunks[this.chunks.length - 1 - i] - value.chunks[value.chunks.length -
     * 1 - i]; this.chunks[this.chunks.length - 2 - i] -= 1; results[results.length
     * - 1 - i] = calculation2;
     * 
     * }
     * 
     * } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * } System.out.println("Answer is: " + answer);
     * 
     * BrobInt finalAnswer = new BrobInt(answer);
     * 
     * return finalAnswer;
     * 
     * // if the second number is larger than the first } else if (this.chunks[0] <
     * value.chunks[0]) { for (int i = 0; i < this.chunks.length; i++) {
     * 
     * if (value.chunks[value.chunks.length - 1 - i] >=
     * this.chunks[this.chunks.length - 1 - i]) { int calculation3 =
     * value.chunks[value.chunks.length - 1 - i] - this.chunks[this.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation3; } else {
     * value.chunks[value.chunks.length - 1 - i] += 10; int calculation4 =
     * value.chunks[value.chunks.length - 1 - i] - this.chunks[this.chunks.length -
     * 1 - i]; value.chunks[value.chunks.length - 2 - i] -= 1;
     * results[results.length - 1 - i] = calculation4; }
     * 
     * } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * }
     * 
     * System.out.println("Answer is: -" + answer); BrobInt calculation = new
     * BrobInt(answer); BrobInt finalAnswer = calculation.multiply(negOne);
     * 
     * return finalAnswer;
     * 
     * // if the numbers both start with the same index } else { BrobInt bigInteger
     * = ZERO; BrobInt smallInteger = ZERO; for (int i = 1; i < value.chunks.length
     * - 1; i++) { if (this.chunks[i] > value.chunks[i]) { bigInteger =
     * this.add(ZERO); smallInteger = value.add(ZERO); break;
     * 
     * } else if (value.chunks[i] > this.chunks[i]) { bigInteger = value.add(ZERO);
     * smallInteger = this.add(ZERO); break; } } // if the second number is the
     * larger number if (value.equals(bigInteger)) { for (int i = 0; i <
     * value.chunks.length - 1; i++) { if (value.chunks[value.chunks.length - 1 - i]
     * >= this.chunks[this.chunks.length - 1 - i]) { int calculation6 =
     * value.chunks[value.chunks.length - 1 - i] - this.chunks[this.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation6;
     * 
     * } else { value.chunks[value.chunks.length - 1 - i] += 10; int calculation7 =
     * value.chunks[value.chunks.length - 1 - i] - this.chunks[this.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation7;
     * value.chunks[value.chunks.length - 2 - i] -= 1;
     * 
     * }
     * 
     * } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * } System.out.println("Your answer is: -" + answer); BrobInt estimation = new
     * BrobInt(answer); BrobInt finalAnswer = estimation.multiply(negOne);
     * 
     * return finalAnswer;
     * 
     * } if (this.equals(bigInteger)) { for (int i = 0; i < value.chunks.length - 1;
     * i++) { if (this.chunks[this.chunks.length - 1 - i] >=
     * value.chunks[value.chunks.length - 1 - i]) { int calculation8 =
     * this.chunks[this.chunks.length - 1 - i] - value.chunks[value.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation8; } else {
     * this.chunks[this.chunks.length - 1 - i] += 10; int calculation7 =
     * this.chunks[this.chunks.length - 1 - i] - value.chunks[value.chunks.length -
     * 1 - i]; results[results.length - 1 - i] = calculation7;
     * this.chunks[this.chunks.length - 2 - i] -= 1;
     * 
     * } } for (int k = 0; k < results.length - 1; k++) { answer +=
     * Integer.toString(results[k]);
     * 
     * } System.out.println("Your answer is: -" + answer); BrobInt estimation = new
     * BrobInt(answer); BrobInt finalAnswer = estimation.multiply(negOne);
     * 
     * return finalAnswer; } } }
     * 
     * }
     */

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

    public boolean equals(BrobInt bint) {
        return ((sign == bint.sign) && (this.toString().equals(bint.toString())));
    }

    public static BrobInt valueOf(long value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public static void main(String[] arguments) {

        System.out.println("\n\n  Making new BrobInt for " + arguments[0]);
        BrobInt box1 = new BrobInt(arguments[0]);
        System.out.println("\n\n  Making new BrobInt for " + arguments[1]);
        BrobInt box2 = new BrobInt(arguments[1]);
        System.out.println("\n\n  Subtracting box1 and box2.... ");
        BrobInt box3 = box1.subtract(box2);
        System.out.println("\n    Your answer is: " + box3.toString());

    }
}
