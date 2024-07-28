import java.util.Scanner;
public class Task1 {
    public static void guessnumbergame() {
        Scanner in = new Scanner(System.in);

        int num = 1 + (int) (100 * Math.random());
        int c = 5;
        int i, g;
        int score = 0;
        int rounds = 3; 

        System.out.println("A number is selected between 1 to 100. Guess the number within 5 trials for each round.");

        for (int round = 1; round <= rounds; round++) {
            System.out.println("\nRound " + round);

            for (i = 0; i < c; ++i) {
                System.out.print("Guess the number: ");
                g = in.nextInt();

                if (num == g) {
                    System.out.println("Congratulations! You guessed the right number!");
                    score += 10 - i; // Award points based on the number of attempts
                    break;
                } else if (num > g && i != c - 1) {
                    System.out.println("The number is Greater than " + g + " Try Again!");
                } else if (num < g && i != c - 1) {
                    System.out.println("The number is Less than " + g + " Try Again!");
                }
            }

            if (i == c) {
                System.out.println("You have reached the maximum number of tries for this round!");
                System.out.println("The number was: " + num);
            }
            num = 1 + (int) (100 * Math.random()); // Generates a new number for the next round
        }

        System.out.println("\nYour final score: " + score); // Display final score after the game
    }

    public static void main(String[] arg) {
        guessnumbergame();
    }
}