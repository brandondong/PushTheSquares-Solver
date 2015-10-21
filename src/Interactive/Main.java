package Interactive;

import Model.BlockColor;
import Model.Board;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Brandon on 2015-10-19.
 */
public class Main {

    public static void main(String[] args) {
        while (true) {
            Board b = initBoard();
            List<BlockColor> solution = b.solve();
            if (solution == null) {
                System.out.println("No solution found. The board may have been entered incorrectly.");
            } else {
                System.out.println("Solution found:");
                System.out.println(solution);
            }
        }
    }

    private static Board initBoard() {
        System.out.println("This is the board setup process, type 'Q' at anytime to quit.");
        System.out.println("Enter width of board:");
        int width = takeNumInput();
        System.out.println("Enter height of board:");
        int height = takeNumInput();

        Board b = new Board(width, height);
        return b;
    }

    // Effects: allows the user to input a number which it returns
    private static int takeNumInput() {
        while (true) {
            try {
                Scanner reader = new Scanner(System.in);
                int n = reader.nextInt();
                return n;
            } catch (Exception e) {
                System.out.println("Invalid number entered. Try again.");
            }
        }
    }
}
