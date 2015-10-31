package Interactive;

import Model.Block;
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
            System.out.println();
        }
    }

    private static Board initBoard() {
        System.out.println("This is the board setup process, type 'Q' at anytime to quit.");
        System.out.println("Enter width of board:");
        int width = takeNumInput(1, 10);
        System.out.println("Enter height of board:");
        int height = takeNumInput(1, 10);

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
            System.out.println("'4' for a colored, movable block");
            System.out.println("'5' for a solid block");
            System.out.println("'0' when you have finished.");
            int input = takeNumInput(0, 5);

            if (input == 0) {
                return;
            } else {
                Point p = inputPoint(b);
                if (input == 1) {
                    Tile c = inputColor();
                    b.addTile(p.x, p.y, c);
                } else if (input == 2) {
                    Tile d = inputDirection();
                    b.addTile(p.x, p.y, d);
                } else if (input == 3) {
                    System.out.println("Input the second point:");
                    Point p2 = inputPoint(b);
                    b.addTP(p.x, p.y, p2.x, p2.y);
                } else if (input == 4) {
                    Tile c = inputColor();
                    Tile d = inputDirection();
                    b.addBlock(new Block(c.color, d.direction, p.x, p.y));
                } else if (input == 5) {
                    b.addTile(p.x, p.y, Tile.SOLID);
                }
                System.out.println(b);
            }
        }
    }

    // Effects: allows the user to input the color represented as the tile
    private static Tile inputColor() {
        while (true) {
            System.out.println("Enter the number corresponding to the color:");
            System.out.println("'1' for blue");
            System.out.println("'2' for red");
            System.out.println("'3' for yellow");
            System.out.println("'4' for green");

            int input = takeNumInput(1, 4);
            switch (input) {
                case 1: return Tile.BLUE;
                case 2: return Tile.RED;
                case 3: return Tile.YELLOW;
                case 4: return Tile.GREEN;
            }
        }
    }

    // Effects: allows the user to input the direction represented as the tile
    private static Tile inputDirection() {
        while (true) {
            System.out.println("Enter the number corresponding to the direction:");
            System.out.println("'1' for up");
            System.out.println("'2' for down");
            System.out.println("'3' for left");
            System.out.println("'4' for right");

            int input = takeNumInput(1, 4);
            switch (input) {
                case 1: return Tile.UP;
                case 2: return Tile.DOWN;
                case 3: return Tile.LEFT;
                case 4: return Tile.RIGHT;
            }
        }
    }

    // Effects: allows the user to input x,y coordinate that are within board
    private static Point inputPoint(Board b) {
        System.out.println("The upper-left tile has the coordinate (1,1).");

        System.out.println("Enter the x-coordinate:");
        int x = takeNumInput(1, b.getWidth()) - 1;

        System.out.println("Enter the y-coordinate:");
        int y = takeNumInput(1, b.getHeight()) - 1;

        return new Point(x,y);
    }

    // Effects: allows the user to input a number which it returns, between low and high
    private static int takeNumInput(int low, int high) {
        while (true) {
            Scanner reader = new Scanner(System.in);
            try {
                String s = reader.next();
                if (s.toLowerCase().equals("q")) {
                    System.out.println("Goodbye.");
                    System.exit(-1);
                }
                int n = Integer.parseInt(s);
                if (n < low || n > high) throw new Exception();

                return n;

            } catch (RuntimeException e) {
                System.out.println("Invalid number entered. Try again.");
            } catch (Exception e) {
                System.out.println("Number is out of bounds. Try again.");
            }
        }
    }
}
