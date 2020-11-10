/**
 * filename : Integratable.java purpose : provides an interface for an
 * integratable function
 * 
 * @author : Dr. Johnson from a file by Dr. Phil Dorin
 * @date : this version created from hardcopy on 2019-01-27
 * @version : 1.0.0 Description : An object may possess an integratable
 *          function,
 *
 *          <code>f: double -> double</code>
 *
 *          as long as its class implements the Integratable interface. For
 *          example, if we have:
 *
 *          <pre>
 *          class SquaringFunction implements IntegratableFunction { public
 *          double f( double x ) { return x * x; } } </code>
 *
 *          then the code line
 *
 *          <code>SquaringFunction sqrf = new SquaringFunction();</code>
 *
 *          creates a new object referred to by the token
 *          <q>sqrf</q> which possesses an instance method referred to by the
 *          token
 *          <q>f</q>, which is a function that can be evaluated at a given point
 *          [the value of the argument
 *          <q>x</q>] as in
 *
 *          <code>double result1 = sqrf.f( 3.14159 );</code>
 *
 *          <code>double result2 = sqrf.f( Math.random() );</code>
 *
 *          etc.
 */

public interface Integratable {
   public double f(double x);

   public static double calcY(double[] coefficients, double midPoint) {
      double y = 0;

      for (int i = 0; i < coefficients.length; i++) {
         y += coefficients[i] * Math.pow(midPoint, i);

      }

      return y;

   }

   public static double integratePoly(double[] coefficients, double upperBound, double lowerBound, double n) {
      double midPoint = 0.0;
      double sum = 0.0;
      double yVal = 0.0;
      double sliceWidth = (upperBound - lowerBound) / n;

      for (int j = 0; j < n; j++) {
         yVal = 0.0;
         midPoint = lowerBound + (sliceWidth / 2) + (j * sliceWidth);
         yVal = calcY(coefficients, midPoint);
         sum += yVal * sliceWidth;
      }

      return sum;
   }

   public static double integrateSin(double upperBound, double lowerBound, double n) {
      double midPoint = 0;
      double sum = 0.0;
      double yVal = 0.0;
      double sliceWidth = (upperBound - lowerBound) / n;

      for (int k = 0; k < n; k++) {
         yVal = 0.0;
         midPoint = lowerBound + (sliceWidth / 2) + (k * sliceWidth);
         yVal = Math.sin(midPoint);
         sum += yVal * sliceWidth;
      }

      return sum;

   }

   public static double integrateLog(double upperBound, double lowerBound, double n) {
      double midPoint = 0;
      double sum = 0;
      double yVal = 0.0;
      double sliceWidth = (upperBound - lowerBound) / n;

      for (int l = 0; l < n; l++) {
         yVal = 0.0;
         midPoint = lowerBound + (sliceWidth / 2) + (l * sliceWidth);
         yVal = Math.log(midPoint);
         sum += yVal * sliceWidth;
      }

      return sum;
   }

   public static double integrateExp(double upperBound, double lowerBound, double n) {
      double midPoint = 0;
      double sum = 0;
      double yVal = 0.0;
      double sliceWidth = (upperBound - lowerBound) / n;

      for (int l = 0; l < n; l++) {
         yVal = 0.0;
         midPoint = lowerBound + (sliceWidth / 2) + (l * sliceWidth);
         yVal = Math.exp(midPoint);
         sum += yVal * sliceWidth;
      }

      return sum;

   }

   public static double integrateSqrt(double upperBound, double lowerBound, double n) {
      double midPoint = 0;
      double sum = 0;
      double yVal = 0.0;
      double sliceWidth = (upperBound - lowerBound) / n;

      for (int l = 0; l < n; l++) {
         yVal = 0.0;
         midPoint = lowerBound + (sliceWidth / 2) + (l * sliceWidth);
         yVal = Math.sqrt(midPoint);
         sum += yVal * sliceWidth;
      }

      return sum;

   }
}
