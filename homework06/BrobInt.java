import java.util.Arrays;


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
        System.out.println( "\n   input numbers to add: this: " + this.toString() + " and value: " + value.toString() );
        if (this.equals(BrobInt.ZERO)) {
            return value;
        }
        if (value.equals(BrobInt.ZERO)) {

            return this;
        }
        System.out.println( "\n   input numbers to add: this: " + this.toString() + " and value: " + value.toString() );

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

        System.out.println( "\n   input numbers to add: this: " + this.toString() + " and value: " + value.toString() );
        toArray( smallChunks );   toArray( largeChunks );

        String answer = "";
        int[] result = new int[largeChunks.length + 1];
        if (largeSign == smallSign) {

            for (int i = 0; i < largeChunks.length; i++) {
                int sum = (smallChunks[smallChunks.length - i - 1]) + (largeChunks[largeChunks.length - i - 1]) + carry;

                carry = sum / 10;

                result[largeChunks.length - 1 - i] = sum % 10;

            }
            toArray( result );         // print out result

            for( int i = 0; i < result.length - 1; i++ ) {
               answer += Integer.toString( result[i] );
            }
            System.out.println( "    Answer is: " + answer );

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

   public void toArray( int[] d ) {
      System.out.println( "Array contents: " + Arrays.toString( d ) );
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

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt bint ) {
      return ( (sign == bint.sign) && (this.toString().equals( bint.toString() )) );
   }

    public static BrobInt valueOf(long value) {

        BrobInt calculation = new BrobInt("answer");

        return calculation;

    }

    public static void main(String [] arguments) {

        System.out.println( "\n\n  Making new BrobInt for " + arguments[0] );
        BrobInt box1 = new BrobInt(arguments[0]);
        System.out.println( "\n\n  Making new BrobInt for " + arguments[1] );
        BrobInt box2 = new BrobInt(arguments[1]);
        System.out.println( "\n\n  Adding box1 and box2.... " );
        BrobInt box3 = box1.add(box2);
        System.out.println( "\n    Your answer is: " + box3.toString() );

    }
}



















