import java.util.Arrays;

public class SkateRamp {

    static int[] expValues;

    private static void runMyTests() {

        double[] polyCoefficients = { 1.0, 2.3 };
        double polyPercent = 0.0001;
        double percentChange = 200;
        double currentArea = 0;

        double previousArea = Integratable.integratePoly(polyCoefficients, 3.0, -1.0, 2);
        while (percentChange > polyPercent) {
            currentArea = Integratable.integratePoly(polyCoefficients, 3.0, -1.0, 2);
            percentChange = Math.abs(1 - (currentArea / previousArea));
            previousArea = currentArea;

        }
        System.out.print(
                "Running Poly Test with 1.0 -2.3 -1.0 3.0 with 0.0001% | Expected: 123.45 | Got: " + currentArea);

        previousArea = Integratable.integrateSin(5, 0, 1);

        while (percentChange > 0.01) {
            currentArea = Integratable.integrateSin(5, 0, 2);
            percentChange = Math.abs(1 - (currentArea / previousArea));
            previousArea = currentArea;
        }

        System.out.print("Running Sin Test with 0 and 5 at 0.01% | Expected: 0.94  | Got: " + currentArea);

        previousArea = Integratable.integrateLog(1.1, 2.3, 1);
        while (percentChange > 0.01) {
            currentArea = Integratable.integrateLog(5, 0, 2);
            percentChange = Math.abs(1 - (currentArea / previousArea));
            previousArea = currentArea;
        }

        System.out.println("Running Log Test with 1.2 and 2.3 at 0.01% | Expected: 0.61 | Got: " + currentArea);

    }

    public static void main(String[] args) {
        double epsilonValue = 0.01;
        double percentChange = 200;
        double previousArea = 0;
        double currentArea = 0;
        int n = 2;
        double lowerBound = 0;
        double upperBound = 0;

        double[] coefficients = new double[args.length - 3];

        validateArgs(args);

        if (args[args.length - 1].contains("%")) {

            String[] epsilonConversion = args[args.length - 1].split("%");
            epsilonValue = Double.parseDouble(epsilonConversion[0]);

            lowerBound = Double.parseDouble(args[args.length - 3]);
            upperBound = Double.parseDouble(args[args.length - 2]);

            for (int i = 1; i < args.length - 3; i++) {

                coefficients[i - 1] = Double.parseDouble(args[i]);

            }

        } else {

            lowerBound = Double.parseDouble(args[args.length - 2]);
            upperBound = Double.parseDouble(args[args.length - 1]);

            for (int i = 1; i < args.length - 2; i++) {

                coefficients[i - 1] = Double.parseDouble(args[i]);

            }

        }

        switch (args[0]) {

            case "poly":

                previousArea = Integratable.integratePoly(coefficients, upperBound, lowerBound, 1);

                while (percentChange > epsilonValue) {
                    currentArea = Integratable.integratePoly(coefficients, upperBound, lowerBound, n);
                    percentChange = Math.abs(1 - (currentArea / previousArea));
                    previousArea = currentArea;

                }

                System.out.println("Your current area is: " + currentArea);
                System.out.print("The amount of Plywood needed: " + (currentArea * 3));

                break;

            case "sin":

                previousArea = Integratable.integrateSin(upperBound, lowerBound, 1);

                while (percentChange > epsilonValue) {
                    currentArea = Integratable.integrateSin(upperBound, lowerBound, n);
                    percentChange = Math.abs(1 - (currentArea / previousArea));
                    previousArea = currentArea;
                }

                System.out.print("Your current area is: " + currentArea);
                System.out.println("The amount of Plywood needed: " + (currentArea * 3));

                break;

            case "log":

                previousArea = Integratable.integrateLog(upperBound, lowerBound, 1);

                while (percentChange > epsilonValue) {
                    currentArea = Integratable.integrateLog(upperBound, lowerBound, n);
                    percentChange = Math.abs(1 - (currentArea / previousArea));
                    previousArea = currentArea;
                }

                System.out.print("Your current area is: " + currentArea);
                System.out.println("The amount of Plywood needed: " + (currentArea * 3));

                break;

            case "exp":

                previousArea = Integratable.integrateExp(upperBound, lowerBound, 1);

                while (percentChange > epsilonValue) {
                    currentArea = Integratable.integrateExp(upperBound, lowerBound, n);
                    percentChange = Math.abs(1 - (currentArea / previousArea));
                    previousArea = currentArea;
                }

                System.out.print("Your current area is: " + currentArea);
                System.out.println("The amount of Plywood needed: " + (currentArea * 3));

                break;

            case "sqrt":

                previousArea = Integratable.integrateSqrt(upperBound, lowerBound, 1);

                while (percentChange > epsilonValue) {
                    currentArea = Integratable.integrateSqrt(upperBound, lowerBound, n);
                    percentChange = Math.abs(1 - (currentArea / previousArea));
                    previousArea = currentArea;
                }

                System.out.print("Your current area is: " + currentArea);
                System.out.println("The amount of Plywood needed: " + (currentArea * 3));

                break;

        }

    }

    private static void validateArgs(String[] args) {
        String userInstructions = "In order for the program to run, you must enter the type of function you would like to integrate (sin, poly, log, or exp), then specify the coefficients of the polynomial and, and then finally add the boundaries of the function you are integrating. If you would like, you can optionally add a specific epsilon value for how accurate the estimation should be calculated. However, if no epsilon value is entered, the program will estimate the area to the nearest .001%";

        if (args.length == 0) {
            throw new IllegalArgumentException("You did not enter anything into the program. " + userInstructions);

        }

        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "You did not enter the minimum amount of arguments for in order for the program to run. "
                            + userInstructions);
        }

    }

}