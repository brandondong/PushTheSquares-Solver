package Interactive;

import Model.BlockColor;
import Model.Board;

import java.util.List;

/**
 * Created by Brandon on 2015-10-19.
 */
public class Main {

    public static void main(String[] args) {
        Board b = null;
        while (true) {
            initBoard(b);
            List<BlockColor> solution = b.solve();
            if (solution == null) {
                System.out.println("No solution found. The board may have been entered incorrectly.");
            } else {
                System.out.println("Solution found:");
                System.out.println(solution);
            }
        }
    }

    private static void initBoard(Board b) {
        System.out.println("This is the board setup process, type 'Q' at anytime to quit.");
        System.out.println("Enter width of board:");
        System.out.println("Enter height of board:");
    }
}
