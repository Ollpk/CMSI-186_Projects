/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name : BirthdaySolver.java
 * 
 * Purpose : Create a program that calculates and approximates the probability
 * that, based on a number of people, two ore more share a birthday over a given
 * number of trials/experiments.
 * 
 * @author : Jake Griffin : 2020-12-16
 *         <p>
 *         Description: This program is a program that calculates and
 *         approximates the probability that two or more individuals share the
 *         same birthday. This is based on the number of people in their sample
 *         size and the number of trials/experiments conducted.
 *         <p>
 *         This program is meant to return immedietly and provide a String
 *         written result that makes it easily understandable for the user to
 *         interpret their results.
 * 
 *
 * 
 *         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *         Revision History ================ Ver Date Modified by: Reason for
 *         change or modification ----- ---------- ------------
 *         ---------------------------------------------------------------------
 *         12.16.2020 - Completed methods and added JavaDoc comments
 * 
 *         12.15.2020- Began start on lab/file after having problems with
 *         previous lab.
 *
 *         ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

/**
 * This is the BirthdaySolver class that holds two methods to estimate
 * probability. It contains two methods (calculation and approximation) which
 * work to estimate the probability that two people have the same birthday based
 * on a given number of people. In approximation method, we are not calculating
 * but am approximating the probability over experiments, so that not only
 * depends on the number of people but also on the number of experiments/trials.
 * 
 */
public class BirthdaySolver {
    static int N;

    static int trials;

    /**
     * Method to calculate actual probability that given a certain number of people,
     * two or more share the same birthday.
     * 
     * @param N Integer that determines the number of people that are put in the
     *          sample size for the calculation.
     * @return double that is the % probability that two or more people share the
     *         same birthday.
     */
    public static double calculationProbability(Integer N) {

        int numberPeople = N;
        double[] persons = new double[numberPeople];
        double answer = 1;
        double calculation;

        // Assigns each person a birthday based on different days
        for (int i = 0; i <= persons.length - 1; i++) {
            int personDay = 365 - i;
            persons[i] = (double) personDay / 365;

        }
        // Finds probability that none are the same
        for (int j = 0; j <= persons.length - 1; j++) {
            answer *= (double) persons[j];

        }

        calculation = (1 - answer);
        calculation *= 100;

        return calculation;

    }

    /**
     * Method to approximate the probability that given a certain number of people
     * and certain number or trials/experiments, what is the probability that two or
     * more share the same birthday?
     * 
     * @param N      Integer that represents the number of people that are put in a
     *               sample size to be approximated within each trial/experiment.
     * @param trials Integer that represents the number of times the sample size is
     *               approximated and the number of trials/experiments that the user
     *               wants to have conducted.
     * @return double that is the % probability that two or more share the same
     *         birthday.
     */
    public static double approximationProbability(Integer N, Integer trials) {
        int counter = 0;
        int numberPeople = N;
        int sharedBirthdays = 0;

        for (int i = 0; i < trials; i++) {
            int[] persons = new int[365];
            // Assigns empty array of all 0s
            for (int j = 0; j < persons.length; j++) {
                persons[j] = 0;
            }
            // Finds random birthdate (in b/w 1 - 365) and increases empty array for amount
            // of birthdays in each date
            for (int k = 0; k <= numberPeople; k++) {
                int randomDay = (int) Math.floor(Math.random() * 365);
                persons[randomDay]++;
            }
            // Checks if there is more than one birthday accounted for in each date of the
            // array
            for (int l = 0; l < 365; l++) {
                if (persons[l] > 1) {
                    sharedBirthdays++;
                }
            }
            // Increases counter if it is found that 2 or more people have the same
            // birthday.
            if (sharedBirthdays >= 1) {
                counter++;
            }

        }

        double calculation = (counter / trials) * 100;

        return calculation;
    }

    /**
     * Main method that interprets user arguments taken from command line and
     * converts the users input into the variables that allow for the program to
     * calculate and approximate probability for the user.
     * <p>
     * The program outputs a string that displays two numbers. The first number is
     * the actual probability and the second one is the approximation.
     * 
     * @param args a String array that will take both, the number of people to be
     *             put in a sample size and the number of trials and experiments
     *             that should be used for approximation
     */
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Exactly two arguments are required");
            }
            System.out.println("Setting your first input (" + args[0] + ") as the number of people being sampled.");

            BirthdaySolver.N = Integer.parseInt(args[0]);
            System.out.println("Number of people: " + BirthdaySolver.N);
            if (BirthdaySolver.N < 2) {
                throw new IllegalArgumentException("At least two people are required");
            }
            System.out.println("Setting your second input (" + args[1] + ") as the number of trials");
            BirthdaySolver.trials = Integer.parseInt(args[1]);
            System.out.println("Number of trials: " + BirthdaySolver.trials);
            if (BirthdaySolver.trials < 1) {
                throw new IllegalArgumentException("At least one trial is required");
            }

            System.out.println("Your actual probability is: " + calculationProbability(N) + "%");
            System.out.println("Your approximated probability is: " + approximationProbability(N, trials) + "%");

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }
}