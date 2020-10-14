import java.text.DecimalFormat;

public class Ball {

    private static final double BALL_RADIUS = 4.45; // radius in inches as given by the problem
    private static final double BALL_WEIGHT = 1.0; // weight in pounds as given
    private static final double FRICTION_COEFFICIENT = 0.99; // one percent slowdown per second due to friction
    public static final int X_INDEX = 0;
    public static final int Y_INDEX = 1;
    private static final int TAB_OFFSET_INDEX = 22; // OCD move just to make output look lined up

    private boolean isInBounds = true; // all balls will start in bounds by default
    private double[] centerLocation = new double[2]; // array containing both coordinate values
    private double[] currentSpeed = new double[2]; // array containing both direction speed values
    private double frictionValue = FRICTION_COEFFICIENT; // initialize for one secone default time slice
    Ball[] ballNumber;

    /**
     * Constructor to make a new Ball, no parameters
     * 
     * @param xLocation double-precision value of the X location of the ball
     * @param yLocation double-precision value of the Y location of the ball
     * @param xMovement double-precision value for the initial speed of the ball in
     *                  X direction
     * @param yMovement double-precision value for the initial speed of the ball in
     *                  Y direction
     */
    public Ball(double xLocation, double yLocation, double xMovement, double yMovement) {
        centerLocation[0] = xLocation;
        centerLocation[1] = yLocation;
        currentSpeed[0] = xMovement;
        currentSpeed[1] = yMovement;

    }

    /**
     * method to fetch the current speed of the ball
     * 
     * @return double-precision two-element array of X and Y speed values
     */
    public double[] getCurrentSpeed() { // calculate the current speed.

        return currentSpeed;
    }

    /**
     * method to fetch the current position of the ball
     * 
     * @return double-precision two-element array of X and Y location values
     */
    public double[] getCurrentPosition() {
        return centerLocation;
    }

    /**
     * method to determine if the ball is still moving
     * 
     * @return boolean true if ball is moving, false if at rest Note: at rest is
     *         defined as speed <= 1.0 inch/second
     */
    public boolean isStillMoving() {
        double speed = Math.sqrt(Math.pow(currentSpeed[0], 2) + Math.pow(currentSpeed[1], 2)) / 12; // divide by 12
                                                                                                    // because its 12
                                                                                                    // inches per foot
                                                                                                    // and we have found
                                                                                                    // the speed in
                                                                                                    // feet.
        if (speed <= 1.0) {
            return false;

        } else {

            return true;
        }
    }

    /**
     * method to flag the ball as "out of bounds" which will set its speed to zero
     * and its "isInBounds" value to false so it will effectively no longer be in
     * the simulation
     * 
     * @param fieldWidth  double-precision value of 1/2 the designated field width
     * @param fieldHeight double-precision value of 1/2 the designated field height
     */
    public void setBallOutOfBounds(double fieldWidth, double fieldHeight) {
        centerLocation[0] = fieldWidth + 5;
        centerLocation[1] = fieldHeight + 5;
        this.isInBounds = false;
        currentSpeed[0] = 0;
        currentSpeed[1] = 0;

    }

    /**
     * method to update the speed of the ball for one slice of time
     * 
     * @param timeSlice double-precision value of time slace for simulation
     * @return double-precision two element array of new velocity values
     */
    public double[] updateSpeedsForOneTick(double timeSlice) {
        currentSpeed[0] = applyFriction(currentSpeed[0], timeSlice);
        currentSpeed[1] = applyFriction(currentSpeed[1], timeSlice);

        return currentSpeed;
    }

    public double applyFriction(double speed, double timeSlice) {
        if (speed < 1) {
            return 0;
        } else {
            return speed * Math.pow(1 - frictionValue, timeSlice);
        }
    }

    /**
     * method to update the ball's position and velocity
     * 
     * @param timeSlice double-precision value of time slace for simulation
     */
    public void move(double timeSlice) {
        updateSpeedsForOneTick(timeSlice);
        if (currentSpeed[0] != 0) {
            centerLocation[0] += (timeSlice * currentSpeed[0]);

        }
        if (currentSpeed[1] != 0) {
            centerLocation[1] += (timeSlice * currentSpeed[1]);

        } else {

        }

    }

    /**
     * our venerable "toString()" representation
     * 
     * @return String-y version of what this Ball is
     */
    public String toString() { // outputs the details of the ball class of a particular ball object. This will
                               // be called everytime through the loop.
        DecimalFormat df = new DecimalFormat("##.00");
        String output = "Ball Location X: " + df.format(centerLocation[0]) + " | Ball Location Y: "
                + df.format(centerLocation[1]) + "  | Ball Speed X: " + df.format(currentSpeed[0]) + " | Ball Speed Y: "
                + df.format(currentSpeed[1]);
        return output;
    }

    /**
     * a main method for testing -- pretty simple
     * 
     * @param args[] String array of command line arguments
     */
    public static void main(String args[]) {
        System.out.println("\n   Testing the Ball class................");
        Ball b1 = new Ball(10.0, 50.0, 2.0, 6.0);
        Ball b2 = new Ball(20.0, 60.0, 3.0, 7.0);
        Ball b3 = new Ball(30.0, 70.0, 4.0, 8.0);
        Ball b4 = new Ball(40.0, 80.0, 5.0, 9.0);
        System.out.println("Ball b1: " + b1.toString());
        System.out.println("Ball b2: " + b2.toString());
        System.out.println("Ball b3: " + b3.toString());
        System.out.println("Ball b4: " + b4.toString());
        System.out.println();
        b1.move(1.0);
        b2.move(1.0);
        b3.move(1.0);
        b4.move(1.0);
        System.out.println("Ball b1: " + b1.toString());
        System.out.println("Ball b2: " + b2.toString());
        System.out.println("Ball b3: " + b3.toString());
        System.out.println("Ball b4: " + b4.toString());
        System.out.println();
        b1.move(0.1);
        b2.move(0.1);
        b3.move(0.1);
        b4.move(0.1);
        System.out.println("Ball b1: " + b1.toString());
        System.out.println("Ball b2: " + b2.toString());
        System.out.println("Ball b3: " + b3.toString());
        System.out.println("Ball b4: " + b4.toString());
        System.out.println();
        b1.move(2.0);
        b2.move(2.0);
        b3.move(2.0);
        b4.move(2.0);
        System.out.println("Ball b1: " + b1.toString());
        System.out.println("Ball b2: " + b2.toString());
        System.out.println("Ball b3: " + b3.toString());
        System.out.println("Ball b4: " + b4.toString());
        System.out.println();
        b1.setBallOutOfBounds(30.0, 30.0);
        for (int i = 0; i < 250; i++) {
            b1.move(2.0);
            b2.move(2.0);
            b3.move(2.0);
            b4.move(2.0);
            System.out.println("Ball b1: " + b1.toString());
            System.out.println("Ball b2: " + b2.toString());
            System.out.println("Ball b3: " + b3.toString());
            System.out.println("Ball b4: " + b4.toString());

            if (b1.isStillMoving() || b2.isStillMoving() || b3.isStillMoving() || b4.isStillMoving()) {
                System.out.println();
            } else {
                break;
            }
        }
    }

}