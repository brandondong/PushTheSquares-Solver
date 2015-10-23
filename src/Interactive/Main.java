package Interactive;

import Model.BlockColor;
import Model.Board;
import Model.Tile;

import java.awt.*;
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
        System.out.println(b);
        initTiles(b);
        return b;
    }

    private static void initTiles(Board b) {
        while (true) {
            System.out.println("Enter the tiles that you want added to the board:");
            System.out.println("'1' for a colored finish tile");
            System.out.println("'2' for a directional switcher tile");
            System.out.println("'3' for a teleporter block");
            System.out.println("'4' for a solid block");
            System.out.println("'0' when you have finished.");
            int input = takeNumInput();
            switch (input) {
                case 0: return;
                case 4:
                    Point p = inputPoint(b);
                    b.addTile(p.x, p.y, Tile.SOLID);
                    break;
                default:
                    System.out.println("Unexpected number entered.");
                    break;
            }
            System.out.println(b);
        }
    }

    // Effects: allows the user to input x,y coordinate that are within board
    private static Point inputPoint(Board b) {
        System.out.println("Enter the x-coordinate (0 based indexing):");
        int x;
        while (true) {
            x = takeNumInput();
            if (b.withinBoardX(x)) break;
            System.out.println("Coordinate is out of bounds.");
        }
        System.out.println("Enter the y-coordinate (0 based indexing):");
        int y;
        while (true) {
            y = takeNumInput();
            if (b.withinBoardY(y)) break;
            System.out.println("Coordinate is out of bounds.");
        }
        return new Point(x,y);
    }

    // Effects: allows the user to input a number which it returns, has to be non-negative
    private static int takeNumInput() {
        while (true) {
            Scanner reader = new Scanner(System.in);
            try {
                String s = reader.next();
                if (s.toLowerCase().equals("q")) {
                    System.out.println("Goodbye.");
                    System.exit(-1);
                }
                int n = Integer.parseInt(s);
                if (n < 0) throw new Exception();
                return n;
            } catch (Exception e) {
                System.out.println("Invalid number entered. Try again.");
            }
        }
    }
}
