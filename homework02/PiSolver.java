import java.util.Random;

/* This is a program which provides an estimation of Pi 
via simulating throwing darts as a Monte Carlo Method. 
*/

public class PiSolver {

  public static void main(String[] args) {

    try {
      if (args.length < 1) {
        throw new IllegalArgumentException("Must have an entry of darts to throw for in order to make your estimation");
      }

      int darts = Integer.parseInt(args[0]);

      if (darts < 1) {
        throw new IllegalArgumentException(
            "You need to throw at least one dart for in order for there to be an estimation");
      }

      System.out.println("Your estimation based off of " + darts + " dart throws is: " + (double) estimate(darts));

    } catch (NumberFormatException e) {
      System.out.println("x");
    }

  }

  private static double estimate(int i) {

    double dartsInside = 0;

    for (int dartTosses = 0; dartTosses < i; dartTosses++) {
      Random x = new Random();
      Random y = new Random();
      double x1 = 0;
      double y1 = 0;
      double randomX = -1 + (1 - -1) * x.nextDouble();
      double randomY = -1 + (1 - -1) * y.nextDouble();

      double distance = Math.sqrt(Math.pow(randomX - x1, 2) + Math.pow(randomY - y1, 2));

      if (distance <= 1) {
        dartsInside++;
      }

    }

    double result = (dartsInside / i) * 4;

    return result;

  }
}
