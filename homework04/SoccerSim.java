import java.text.DecimalFormat;

public class SoccerSim {

    private static final double DEFAULT_FIELD_WIDTH = 1000.0;
    private static final double DEFAULT_FIELD_HEIGHT = 1000.0;
    private static final double DEFAULT_POLE_X_POS = 321.0;
    private static final double DEFAULT_POLE_Y_POS = 543.0;
    private static final double DEFAULT_TIME_SLICE = 1.0;

    private boolean initialReport = true;
    private boolean stillMoving = true;
    private boolean timeSliceDefault = true;
    private double timeSlice = DEFAULT_TIME_SLICE;
    private double totalTimeSecs = 0.0;
    private double[] fieldSizes = new double[] { DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT };
    private double[] poleLocation = new double[] { 355, 355 };

    private int ballCount = 0;

    private Ball[] soccerBalls = null;
    private Clock c = null;

    private static final String USAGE_MESSAGE = "\n  Usage:"
            + "\n    java SoccerSim <ball-parameters> ... <ball-parameters> [timeslice]"
            + "\n      where EACH <ball-parameters> field is a 4-tuple of the values:"
            + "\n            starting-x, starting-y, start-speed-x, start-speed-y" + "\n"
            + "\n    try running with three soccer balls: <10 10 1 1>, <18 18 -1 -1>, and <311 311 1 1>"
            + "\n    this should produce a collision between the first two balls in about four iterations."
            + "\n\n  Please try again.";

    /**
     * constructor
     */

    public SoccerSim() {
        this.c = new Clock();

    }

    /**
     * Method to validate the input arguments
     * 
     * @param arguments String array of the arguments supplied to the program
     */
    public void validateArgsAndSetupSim(String arguments[]) throws NumberFormatException, IllegalArgumentException {

        // no arguments specified, so dispay usage message
        if (0 == arguments.length) {
            System.out.println(USAGE_MESSAGE);
            System.exit(0);

            // validation: if count % 4 is 1, assume last is timeslice in seconds
        } else {
            if (1 == (arguments.length % 4)) { // optional time slice argument exists
                try {
                    double argTimeSlice = Double.parseDouble(arguments[arguments.length - 1]);
                    if (argTimeSlice <= 0) {
                        throw new IllegalArgumentException("Can't have negative or 0 time slices");
                    }
                    timeSlice = argTimeSlice;
                    timeSliceDefault = false;

                } catch (Exception e) {
                    throw new IllegalArgumentException("Can't read time slice input");

                }

            }

            // now handle and validate the ball arguments
            // also check if the timeSlice is the default value or not

            // next is a loop to create all the soccerBalls and put them into the array
            // you will need to replace "true" with the proper condition
            int i = 0;

            soccerBalls = new Ball[arguments.length / 4]; // Isn't an array within an array; just creates a new empty
                                                          // array for soccerballs that is the proper size.

            while (i < (timeSliceDefault ? arguments.length : arguments.length - 1)) {

                try {

                    double xLocation = Double.parseDouble(arguments[i++]); // with i++, the program finds the argument
                                                                           // of i
                    // first and then increases by one for next
                    // variable.
                    double yLocation = Double.parseDouble(arguments[i++]);
                    double xSpeed = Double.parseDouble(arguments[i++]);
                    double ySpeed = Double.parseDouble(arguments[i++]);
                    soccerBalls[ballCount++] = new Ball(xLocation, yLocation, xSpeed, ySpeed);

                } catch (Exception e) {
                    throw new IllegalArgumentException(
                            "Must throw a number (with or without decimal values) for in order for the program to run");
                }

            }

        }
    }

    /**
     * method to report the status of the simulation for every clock tick
     * 
     * @param c Clock object which keeps track of time NOTE: this method calls the
     *          clock.tick() method to get one second to elapse
     */
    public void report() {
        String output = "";
        if (initialReport) {
            initialReport = false;
            output += "\nFIELD SIZE IS " + fieldSizes[0] + " by " + fieldSizes[1];
            output += " - POLE LOCATION @ " + poleLocation[0] + ", " + poleLocation[1];
            output += "\nTIMESLICE VALUE IS: " + this.timeSlice + " second";
            output += (timeSlice > 1.0) ? "s" : "";
            output += " - FRICTION COEFFICIENT IS: " + (1.0 - (0.01 * timeSlice)) + "\n";
            output += "\nINITIAL REPORT at 00:00:00.0000";
            for (int i = 0; i < this.ballCount; i++) {
                output += "\nBall " + (i + 1) + ":\t" + soccerBalls[i].toString();

            }
            // put in some code here to output the report at time zero}
        } else {
            output += "\nPROGRESS REPORT at " + c.toStringColons();
            for (int i = 0; i < soccerBalls.length; i++) {
                output += "\nBall " + (i + 1) + ":\t" + soccerBalls[i].toString();
                // put in some code here to output the report at all other times
            }
        }
        System.out.println(output);
    }

    /**
     * method to move the balls in the soccerBall array
     *
     */
    public void simUpdate() {

        for (int i = 0; i < soccerBalls.length; i++) {
            if (soccerBalls[i].checkIfOutBounds(DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT)) {
                soccerBalls[i].setBallOutOfBounds(DEFAULT_FIELD_WIDTH, DEFAULT_FIELD_HEIGHT);

            } else {
                soccerBalls[i].move(this.timeSlice);
            }

        }
    }

    /**
     * method to check for a collision
     *
     */
    public int[] collisionCheck() {
        int[] ballsCollided = new int[2];
        double distance = 0.0;
        ballsCollided[0] = -99;
        ballsCollided[1] = -99;
        for (int i = 0; i < soccerBalls.length - 1; i++) {
            for (int j = i + 1; j < soccerBalls.length; j++) {
                distance = Math.sqrt((Math.pow((soccerBalls[i].getCurrentPosition()[Ball.X_INDEX]
                        - soccerBalls[j].getCurrentPosition()[Ball.X_INDEX]), 2))
                        + (Math.pow((soccerBalls[i].getCurrentPosition()[Ball.Y_INDEX]
                                - soccerBalls[j].getCurrentPosition()[Ball.Y_INDEX]), 2)));
                if ((distance * 12.0) <= 8.9) {
                    ballsCollided[0] = i;
                    ballsCollided[1] = j;
                    return ballsCollided;
                }
            }
        }
        for (int i = 0; i < soccerBalls.length; i++) {
            distance = Math.sqrt((Math
                    .pow((soccerBalls[i].getCurrentPosition()[Ball.X_INDEX] - poleLocation[Ball.X_INDEX]), 2))
                    + (Math.pow((soccerBalls[i].getCurrentPosition()[Ball.Y_INDEX] - poleLocation[Ball.Y_INDEX]), 2)));
            if ((distance * 12.0) <= 8.9) {
                ballsCollided[0] = i;
                ballsCollided[1] = -1;
                return ballsCollided;
            }
        }
        return ballsCollided;
    }

    /**
     * method to check if at LEAST one ball is still moving
     *
     */
    public boolean atLeastOneBallStillMoving() {
        for (int i = 0; i < soccerBalls.length; i++) {
            if (soccerBalls[i].isStillMoving()) {
                return true;
            }
        }
        return false;
    }

    /**
     * main method of the simulation
     * 
     * @param args String array of the command line arguments
     */
    public static void main(String args[]) {
        System.out.println("\n  Hello, world, from the SoccerSim program!");
        SoccerSim ss = new SoccerSim();

        try {
            ss.validateArgsAndSetupSim(args);
        } catch (NumberFormatException nfe) {
            System.out.println(USAGE_MESSAGE);
            System.exit(1);
        } catch (IllegalArgumentException iae) {
            System.out.println(USAGE_MESSAGE);
            System.exit(2);
        }

        ss.report();
        int iterations = 1; // this is optional
        int[] collision = new int[2];
        while (true) {
            ss.c.tick(0.0, 0.0, 0.0, ss.timeSlice);
            ss.simUpdate();
            ss.report();
            collision = ss.collisionCheck();
            if (collision[1] != -99) {
                if (collision[1] == -1) {
                    System.out.println("\nCollision occurred between ball " + (collision[0] + 1) + " and the pole.");
                } else {
                    System.out.println("\nCollision occurred between ball " + (collision[0] + 1) + " and ball "
                            + (collision[1] + 1) + "... stopping sim!");
                }
                break;
            }
            if (!ss.atLeastOneBallStillMoving()) {
                System.out.println("\nAll soccer balls have stopped moving, stopping sim......");
                System.out.println("NO COLLISION POSSIBLE!");
                break;
            }
            iterations++;
        }
        System.out.println("Simulation required " + iterations + " iterations to complete.");
    }

}
