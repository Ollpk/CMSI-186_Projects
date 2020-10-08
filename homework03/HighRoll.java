import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

    public static void main(String args[]) {

        if (Integer.parseInt(args[0]) < 1) {
            throw new IllegalArgumentException("You need at least 1 dice in your set");
        }
        if (Integer.parseInt(args[1]) < 4) {
            throw new IllegalArgumentException("Need at least four sides for your set");
        }

        int count = Integer.parseInt(args[0]);
        int sides = Integer.parseInt(args[1]);

        DiceSet ds = new DiceSet(count, sides);

        System.out.println("\n   Welcome to the High Roll Game!!\n");
        System.out.println("\n   Press '1' to roll all the dice \n");
        System.out.println("\n   Press '2' and specify which die by order for in order to roll a single die\n");
        System.out.println("\n   Press '3' to calculate the score for this set\n");
        System.out.println("\n   Press '4' to Save this Score as High Score\n");
        System.out.println("\n   Press '5' to Display the High Score\n");
        System.out.println("     Press the 'q' key to quit the program.");

        // This line uses the two classes to assemble an "input stream" for the user to
        // type
        // text into the program
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        var highscore = 0;
        var savedScore = 0;
        while (true) {
            System.out.print("prompt >>");
            String inputLine = null;

            try {

                inputLine = input.readLine();
                if (0 == inputLine.length()) {
                    System.out.println("enter some text!:");
                }
                System.out.println("   You typed: " + inputLine);
                if ('q' == inputLine.charAt(0)) {
                    break;
                }
                if ('1' == inputLine.charAt(0)) {
                    ds.roll();
                    System.out.println(" You have rolled your dice and here are your dice values: " + ds.toString());
                }
                if ('2' == inputLine.charAt(0)) {
                    if (0 == inputLine.length() || inputLine.length() < 2) {
                        throw new IllegalArgumentException(
                                "You need another value entered in addition to two with your initital command in order to roll a specific die");

                    }
                    String[] valueEntered = inputLine.split("\\s");
                    String s = valueEntered[1];
                    Integer indDie = Integer.parseInt(s);
                    ds.rollIndividual(indDie);
                    System.out.println("You entered " + indDie
                            + " and your value of that dice after having rolled it is: " + ds.getIndividual(indDie));

                }

                if ('3' == inputLine.charAt(0)) {

                    int calcAnswer = ds.sum();
                    System.out.println("Current Score Calculated For this Set: " + calcAnswer);
                    savedScore = calcAnswer;

                }

                if ('4' == inputLine.charAt(0)) {
                    highscore = savedScore;
                    System.out.println(
                            "Confirmed: You have saved your roll total of " + highscore + " as your highscore.");

                }
                if ('5' == inputLine.charAt(0)) {
                    if (0 == highscore) {
                        System.out.println("You do not have a high score yet. It is currently 0");
                    } else {
                        System.out.println("Your current high score is this: " + highscore);
                    }

                }

            } catch (IOException ioe) {

                System.out.println("Caught IOException");
            }
        }
    }
}
