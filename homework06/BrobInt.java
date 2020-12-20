import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO = new BrobInt("0");
   public static final BrobInt ONE = new BrobInt("1");
   public static final BrobInt TEN = new BrobInt("10");
   public static final BrobInt NEGONE = new BrobInt("-1");

   public int sign;
   public int bsign;
   public int[] chunks;
   public String internalValue = ""; // internal String representation of this BrobInt
   private String reversed = ""; // the backwards version of the internal String representation
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON = false;

   public BrobInt(String value) {
      // May want to create a method that chunks any extra digits. For instance, if
      // there is 00000045454, we want to get rid of the zeros.
      internalValue = value;
      reversed = new String(new StringBuffer(internalValue).reverse());
      if (value.charAt(0) == '+') {
         bsign = 0;
         reversed = reversed.substring(0, reversed.length() - 1);
      } else if (value.charAt(0) == '-') {
         bsign = 1;
         reversed = reversed.substring(0, reversed.length() - 1);
      }
      value = stringSignConversion(internalValue);
      chunks = new int[value.length()];
      for (int i = 0; i < chunks.length; i++) {
         chunks[i] = Integer.parseInt(String.valueOf(value.charAt(i)));
      }
   }

   public String stringSignConversion(String value) {
      int index = 0;
      if (value.charAt(0) == '-') {
         sign = -1;
         index = 1;
      } else if (value.equals("0")) {
         sign = 0;
      } else {
         sign = 1;
      }
      return value.substring(index);

   }

   /**
    * add method
    */
   public BrobInt add(BrobInt bint) {
      boolean carry = false; // handles the carry for addition
      boolean posResult = true; // true if result is positive
      BrobInt me = null; // for use in calling "subtract()" for when there are
      BrobInt she = null; // opposite signs [take smaller from larger, use larger's sign]

      // dimension the byte arrays and set the shortest length tracker (i.e. "j") to
      // zero
      int[] a = new int[reversed.length()];
      int[] b = new int[bint.reversed.length()];
      int[] c = new int[(Math.max(reversed.length(), bint.reversed.length())) + 2];

      int j = 0;
      String result_s = null;
      StringBuffer result = null;

      // handle opposite signs: need to determine largest absolute value then take the
      // smaller from the larger and use the sign of the larger
      if (bint.bsign != bsign) {
         if (INFO_ON) {
            System.out.println("   ===DIFFERENT SIGNS===");
         }
         if (1 == bint.bsign) {
            // makes "she" the absolute value of bint
            she = new BrobInt(bint.toString().substring(1, bint.toString().length()));
            me = subtract(she);
            if (INFO_ON) {
               System.out.println("      she is: " + she.toString());
            }
            if (INFO_ON) {
               System.out.println("       me is: " + me.toString());
            }
            return me;
         } else if (1 == bsign) {
            me = new BrobInt(toString().substring(1, toString().length())); // "me" abs of this
            she = bint.subtract(me);
            if (INFO_ON) {
               System.out.println("       me is: " + me.toString());
            }
            if (INFO_ON) {
               System.out.println("      she is: " + she.toString());
            }
            return she;
         }
      }

      // assign the values to each int array
      for (int i = 0; i < a.length; i++) {
         a[i] = reversed.charAt(i) - 48; // NOTE: only works for ASCII
      }
      for (int i = 0; i < b.length; i++) {
         b[i] = bint.reversed.charAt(i) - 48; // NOTE: only works for ASCII
      }
      if (INFO_ON) {
         toArray(a);
      }
      if (INFO_ON) {
         toArray(b);
      }

      // perform the addition for the shortest part
      for (int i = 0; i < (Math.min(a.length, b.length)); i++) {

         // do the addition part
         if (carry) {
            c[i] = a[i] + b[i] + 1;
         } else {
            c[i] = a[i] + b[i];
         }

         // handle the carry part
         if (c[i] > 9) {
            carry = true;
            c[i] = c[i] - 10;
         } else {
            carry = false;
         }
         j++; // brute force to keep track of the end of the shortest array for later
      }
      if (INFO_ON) {
         toArray(a);
         toArray(b);
         toArray(c);
      }

      // if they aren't the same length, copy the rest of the numbers from
      // the longer number into the result, rippling the carry across if needed
      if (a.length < b.length) {
         for (int i = j; i < b.length; i++) {
            if (carry) {
               c[i] = b[j++] + 1;
               if (c[i] > 9) {
                  carry = true;
                  c[i] = c[i] - 10;
               } else {
                  carry = false;
               }
            } else {
               c[i] = b[i];
            }
         }
      } else if (a.length > b.length) {
         for (int i = j; i < a.length; i++) {
            if (carry) {
               c[i] = a[j++] + 1;
               if (c[i] > 9) {
                  carry = true;
                  c[i] = c[i] - 10;
               } else {
                  carry = false;
               }
            } else {
               c[i] = a[i];
            }
         }
      }

      // build the result string
      result = new StringBuffer();
      for (int i = 0; i < Math.max(a.length, b.length); i++) {
         result = result.append((int) c[i]);
      }
      if (carry) {
         result = result.append('1');
      }
      if (bsign == 1) {
         result = result.append("-");
      }
      result_s = new String(result.reverse());

      return new BrobInt(result_s);
   }

   public void toArray(int[] d) {
      System.out.println("Array contents: " + Arrays.toString(d));

   }

   /**
    * subtract method
    */
   public BrobInt subtract(BrobInt bint) {
      boolean borrow = false; // handles the borrow for addition
      boolean negative = false; // set to true if result should be negative
      byte[] a = null;
      byte[] b = null;
      int j = 0;
      int mySign = 0; // initialize to positive
      int bintSign = 0; // initialize to positive
      String result_s = null;
      StringBuffer result = null;
      boolean caseNine = false; // Tracks if both negative but bint is longer, so MORE negative

      // set up for the sign handling to decide what to do based on the following
      // cases:
      // 1. no signs at all, this item larger than argument: simple subtraction a - b
      // 2. both signs positive, this item larger than argument: simple subtraction a
      // - b
      // 3. one positive one no sign, this item larger than arg: simple subtraction a
      // - b
      // 4. no signs at all, this item smaller than argument: swap a & b, subtract a -
      // b, result negative
      // 5. both signs positive, this item smaller than argument: swap a & b, subtract
      // a - b, result negative
      // 6. one positive one no sign, this item smaller than arg: swap a & b, subtract
      // a - b, result negative
      // 7. this no sign or positive, arg negative: remove neg from arg and call
      // this.add( arg )
      // 8. this negative, arg positive: add negative to arg and call this.add( arg )
      // 9. both signs negative, this larger abs than arg abs: remove signs, subtract,
      // add neg to result
      // 10. both signs negative, this smaller abs than arg abs: remove signs, swap a
      // & b, subtract, result pos
      //
      // set the sign for 'this'; already initialized to zero for positive default, so
      // check for minus
      // NOTE: signs removed in the constructors and "sign" values set for easier
      // handling
      mySign = bsign;
      bintSign = bint.bsign;

      // check to set the negative boolean for later or perform addition instead
      if ((0 == mySign) && (0 == bintSign) && (0 <= this.compareTo(bint))) { // case 1 - 3
         negative = false;
      } else if ((0 == mySign) && (0 == bintSign) && (0 > this.compareTo(bint))) { // case 4 - 6
         negative = true;
      } else if ((0 == mySign) && (1 == bintSign) && (0 < this.compareTo(bint))) { // case 7
         BrobInt bint1 = new BrobInt(new String(bint.internalValue.substring(1, bint.internalValue.length())));
         return this.add(bint1);
      } else if ((1 == mySign) && (0 == bintSign) && (0 > this.compareTo(bint))) { // case 8
         negative = true;
         BrobInt gMe = new BrobInt(new String(internalValue.substring(1, internalValue.length())));
         BrobInt myResult = new BrobInt(new String("-" + gMe.add(bint).toString()));
         return myResult;
      } else if ((1 == mySign) && (1 == bintSign) && (0 < this.compareTo(bint))) { // case 9
         negative = true;
         caseNine = true;
      } else if ((1 == mySign) && (1 == bintSign) && (0 > this.compareTo(bint))) { // case 10
         negative = false;
      }

      // this is the case for which both signs are negative, but bint is a longer
      // string than this, so
      // it is actually MORE negative. Thus we are subtracting a negative from a
      // negative, which is the
      // same as ADDING the negative to a positive. For example, -999 minus -9999
      // should be the same
      // as -999 plus 9999. So we need to ADD
      if (caseNine) {
         return add(new BrobInt(bint.internalValue.substring(1)));

         // dimension the byte arrays and initialize them appropriately for their
         // lengths;
         // 'a' gets the longer string; if they are the same length, 'a' gets the bigger
         // value
      } else if ((reversed.length() > bint.reversed.length()) || (0 <= this.compareTo(bint))) {
         a = new byte[reversed.length()];
         for (int i = 0; i < a.length; i++) {
            a[i] = (byte) ((int) (reversed.charAt(i)) - 48); // NOTE: only works for ASCII
         }
         b = new byte[bint.reversed.length()];
         for (int i = 0; i < b.length; i++) {
            b[i] = (byte) ((int) (bint.reversed.charAt(i)) - 48); // NOTE: only works for ASCII
         }
      } else {
         a = new byte[bint.reversed.length()];
         for (int i = 0; i < a.length; i++) {
            a[i] = (byte) ((int) (bint.reversed.charAt(i)) - 48); // NOTE: only works for ASCII
         }
         b = new byte[reversed.length()];
         for (int i = 0; i < b.length; i++) {
            b[i] = (byte) ((int) (reversed.charAt(i)) - 48); // NOTE: only works for ASCII
         }
      }
      byte[] c = new byte[(Math.max(reversed.length(), bint.reversed.length())) + 2];

      // perform the subtraction for the shortest part
      // if a borrow is required, loop through from the current location to the end of
      // the input,
      // if necessary, to find the first non-zero value from which to borrow; that one
      // gets decremented
      // and all the ones in between that used to be zero become nines.
      // perform the subtraction for the shortest part
      for (int i = 0; i < (Math.min(a.length, b.length)); i++) {
         if (a[i] < b[i]) {
            a[i] += 10;
            a[i + 1]--;
         }
         c[i] = (byte) ((int) a[i] - (int) b[i]);
         j++; // brute force end of the shortest array for later
      }

      // propagate the borrow across the longer number if necessary
      int k = j;
      if (a.length != b.length) {
         while (a[k] == -1) {
            a[k++] += 10;
            if (k == a.length) {
               break;
            }
            a[k]--;
         }
      }

      // if they aren't the same length, copy the rest of the numbers from
      // the longer number into the result, rippling the borrow across if needed
      if (a.length < b.length) {
         for (int i = j; i < b.length; i++) {
            if (borrow) {
               c[i] = (byte) ((int) b[j] + 1);
               if (c[i] > 9) {
                  borrow = true;
                  c[i] = (byte) ((int) c[i] - 10);
               } else {
                  borrow = false;
               }
            } else {
               c[i] = b[i];
            }
         }
      } else if (a.length > b.length) {
         for (int i = j; i < a.length; i++) {
            if (borrow) {
               c[i] = (byte) ((int) a[j] + 1);
               if (c[i] > 9) {
                  borrow = true;
                  c[i] = (byte) ((int) c[i] - 10);
               } else {
                  borrow = false;
               }
            } else {
               c[i] = a[i];
            }
         }
      }

      // build and return the result string; note, the sign gets handled here
      result = new StringBuffer();
      for (int i = 0; i < Math.max(a.length, b.length); i++) {
         result = result.append((int) c[i]);
      }

      if (borrow) {
         result = result.append('1');
      }

      if (negative) {
         result = result.append("-");
      }

      result_s = new String(result.reverse());
      if ((result_s.charAt(0) == '0') && (!(1 == result_s.length()))) {
         result_s = result_s.substring(1, result_s.length());
      }
      return new BrobInt(result_s);
   }

   /**
    * compareTo method
    */
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

   /**
    * removeLeadingZeros method
    */
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

   /**
    * allZeroDetect method
    */
   public boolean allZeroDetect(BrobInt bint) {
      for (int i = 0; i < bint.toString().length(); i++) {
         if (bint.toString().charAt(i) != '0') {
            return false;
         }
      }
      return true;
   }

   /**
    * multiply method
    */
   public BrobInt multiply(BrobInt value) {

      // check because you need to handle for if the negative is there vs just always
      // substringing

      String base = value.toString();

      if (base.contains("-")) {

         base = base.substring(1);
      }

      int scope = Integer.parseInt(base);

      System.out.println("This is your scope: " + scope);

      BrobInt startValue = new BrobInt("0");

      for (int i = 0; i < scope; i++) {

         startValue = startValue.add(this);

      }
      System.out.println("This is your final calculation: " + startValue);
      if (this.sign == -1 && value.sign == -1) {

         String answer3 = startValue.toString();
         System.out.println("this is your toString: " + answer3);
         answer3 = answer3.substring(1);
         BrobInt finalX = new BrobInt(answer3);
         return finalX;
      } else if (value.sign == -1) {
         String finalCalc = startValue.toString();
         System.out.println("This is your finalCalc: " + finalCalc);

         BrobInt finalY = new BrobInt("-" + finalCalc);

         return finalY;
      }
      return startValue;

   }

   /**
    * divide method
    */
   public BrobInt divide(BrobInt value) {

      boolean negative = false;

      if (this.sign == -1 && value.sign == -1) {

         negative = false;

      } else if (this.sign == -1 || value.sign == -1) {

         negative = true;
      }

      String scopeConvert = this.toString();

      int scope = Integer.parseInt(scopeConvert);

      System.out.println("This is your scope: " + scope);

      int count = 0;
      BrobInt answer = new BrobInt(this.toString());

      String s1 = value.toString();
      int s2 = Integer.parseInt(s1);

      for (int i = 0; i < scope; i++) {

         answer = answer.subtract(value);
         System.out.println("This is your answer in the for loop: " + answer.toString());

         String sCheck = answer.toString();
         int sCheck2 = Integer.parseInt(sCheck);
         System.out.println("This is your int change: " + sCheck2);
         if (sCheck2 < 0) {
            break;
         }
         if (sCheck2 == 0 || sCheck2 >= s2) {
            count++;
            System.out.println("This is your count after first if statement: " + count);

         } else {
            System.out.println("This is proof that the program broke!");
            count++;
            break;
         }

         /*
          * System.out.println(answer.toString()); String answerConvert =
          * answer.toString(); System.out.println("This is your answerConvert: " +
          * answerConvert); int answerInt = Integer.parseInt(answerConvert);
          */

      }
      if (negative = true) {
         count *= -1;
      }

      System.out.println("This is your count (after for loop): " + count);

      System.out.println("This is your updated answer: " + answer.toString());

      String countConvert = Integer.toString(count);

      BrobInt calculation = new BrobInt(countConvert);

      return calculation;

   }

   /**
    * remainder method
    */
   public BrobInt remainder(BrobInt value) {

      int check1 = this.compareTo(value);

      if (check1 == 0) {
         return ZERO;
      } else if (check1 == -1) {
         throw new IllegalArgumentException("There is no remainder to  " + this.toString() + " % " + value.toString()
               + "  because the first number does not go into the second number at all to even leave a remainder.");
      }

      boolean negative = false;

      if (this.sign == -1 && value.sign == -1) {
         negative = false;
      } else if (this.sign == -1 || value.sign == -1) {
         negative = true;
      }
      String scope = this.toString();

      int scopeConvert = Integer.parseInt(scope);

      if (scopeConvert < 0) {
         scopeConvert *= -1;
      }
      BrobInt answer = this;
      String secondString = value.toString();
      int secondInt = Integer.parseInt(secondString);
      for (int i = 0; i < scopeConvert; i++) {
         answer = answer.subtract(value);
         String answerString = answer.toString();
         int answerInt = Integer.parseInt(answerString);
         if (answerInt == secondInt) {
            return ZERO;
         } else if (answerInt < secondInt) {
            return answer;
         }

      }

      BrobInt calculation = new BrobInt("0");

      return calculation;

   }

   /**
    * toString method
    */
   public String toString() {

      String n = sign >= 0 ? "" : "-";

      for (int i = 0; i < chunks.length; i++) {
         n += String.valueOf(chunks[i]);
      }

      return n;

   }

   /**
    * equals method
    */
   public boolean equals(BrobInt bint) {
      return ((sign == bint.sign) && (this.toString().equals(bint.toString())));
   }

   /**
    * valueOf method
    */
   public static BrobInt valueOf(long value) {

      BrobInt calculation = new BrobInt("0");

      return calculation;

   }

   /**
    * main method
    */
   public static void main(String[] arguments) {

      System.out.println("\n\n  In Main: Making new BrobInt for " + arguments[0]);
      BrobInt box1 = new BrobInt(arguments[0]);
      System.out.println("  In Main: Making new BrobInt for " + arguments[1]);
      BrobInt box2 = new BrobInt(arguments[1]);
      BrobInt box3;
      /*
       * /* System.out.println("  In Main: Adding box1: " + box1.toString() +
       * " and box2: " + box2.toString() + ".... "); BrobInt box3 = box1.add(box2);
       *//*
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt("-" + arguments[0]); box2 = new BrobInt("-" +
          * arguments[1]); System.out.println("  In Main: Adding box1: " +
          * box1.toString() + " and box2: " + box2.toString() + ".... "); box3 =
          * box1.add(box2); System.out.println("  In Main: Your answer is: " +
          * box3.toString() + "\n");
          * 
          * box1 = new BrobInt(arguments[0]); box2 = new BrobInt("-" + arguments[1]);
          * System.out.println("  In Main: Adding box1: " + box1.toString() +
          * " and box2: " + box2.toString() + ".... "); box3 = box1.add(box2);
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt("-" + arguments[0]); box2 = new BrobInt(arguments[1]);
          * System.out.println("  In Main: Adding box1: " + box1.toString() +
          * " and box2: " + box2.toString() + ".... "); box3 = box1.add(box2);
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt(arguments[0]); box2 = new BrobInt(arguments[1]);
          * System.out.println("  In Main: Subtracting box1: " + box1.toString() +
          * " and box2: " + box2.toString() + ".... "); box3 = box1.subtract(box2);
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt(arguments[0]); box2 = new BrobInt("-" + arguments[1]);
          * System.out.println("  In Main: Subtracting box1: " + box1.toString() +
          * " and box2: " + box2.toString() + ".... "); box3 = box1.subtract(box2);
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt("-" + arguments[0]); box2 = new BrobInt(arguments[1]);
          * System.out.println("  In Main: Subtracting box1: " + box1.toString() +
          * " and box2: " + box2.toString() + ".... "); box3 = box1.subtract(box2);
          * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
          * 
          * box1 = new BrobInt("-" + arguments[0]); box2 = new BrobInt("-" +
          * arguments[1]); System.out.println("  In Main: Subtracting box1: " +
          * box1.toString() + " and box2: " + box2.toString() + ".... "); box3 =
          * box1.subtract(box2); System.out.println("  In Main: Your answer is: " +
          * box3.toString() + "\n");
          */
      /*
       * box1 = new BrobInt(arguments[0]); box2 = new BrobInt(arguments[1]);
       * System.out.println("  In Main: Multiplying box1: " + box1.toString() +
       * " and box2: " + box2.toString() + ".... "); box3 = box1.multiply(box2);
       * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
       * 
       * box1 = new BrobInt(arguments[0]); box2 = new BrobInt(arguments[1]);
       * System.out.println("  In Main: DIVIDING box1: " + box1.toString() +
       * " and box2: " + box2.toString() + ".... "); box3 = box1.divide(box2);
       * System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");
       */

      box1 = new BrobInt(arguments[0]);
      box2 = new BrobInt(arguments[1]);
      System.out.println("  In Main: REMAINDER box1: " + box1.toString() + " and box2: " + box2.toString() + ".... ");
      box3 = box1.remainder(box2);
      System.out.println("  In Main: Your answer is: " + box3.toString() + "\n");

   }
}
