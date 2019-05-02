import java.io.*;
import java.util.*;

public class GameHelper {

    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt) {
        String inputLine = null;
        System.out.print(prompt + " ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }

    /*
    Note: For extra credit, you might try ‘un-commenting’ the System.out.print(ln)’s in the placeShip( ) method, just
to watch it work! These print statements will let you “cheat”
by giving you the location of the Ships, but it will help you test it.
     */
    public ArrayList<String> placeShip(int comSize) {
        // holds ‘f6’ type coords
        ArrayList<String> alphaCells = new ArrayList<String>();
        // temporary String for concat
        String temp = null;
        // current candidate coordinates
        int[] coords = new int[comSize];
        // current attempts counter
        int attempts = 0;
        // flag = found a good location ?
        boolean success = false;
        // current starting location
        int location = 0;
        // nth ship to place
        comCount++;
        // set horizontal increment
        int incr = 1;
        // if odd ship (place vertically)
        if ((comCount % 2) == 1) {
            // set vertical increment
            incr = gridLength;
        }
        // main search loop (32)
        while (!success & attempts++ < 200) {
            // get random starting point
            location = (int) (Math.random() * gridSize);
                //System.out.print(" try " + location);
            // nth position in ship to place
            int x = 0;
            // assume success
            success = true;
            // look for adjacent unused spots
            while (success && x < comSize) {
                // if not already used
                if (grid[location] == 0) {
                    // save location
                    coords[x++] = location;
                    // try ‘next’ adjacent
                    location += incr;
                    // out of bounds - ‘bottom’
                    if (location >= gridSize) {
                        // failure
                        success = false;
                    }
                    // out of bounds - right edge
                    if (x > 0 && (location % gridLength == 0)) {
                        // failure
                        success = false;
                    }
                    // found already used location
                } else {
                        // System.out.print(" used " + location);
                    // failure
                    success = false;
                }
            }
        }

        int x = 0;
        // turn location into alpha coords
        int row = 0;
        int column = 0;
        // System.out.println("\n");
        while (x < comSize) {
            // mark master grid pts. as ‘used’
            grid[coords[x]] = 1;
            // get row value
            row = (int) (coords[x] / gridLength);
            // get numeric column value
            column = coords[x] % gridLength;
            // convert to alpha
            temp = String.valueOf(alphabet.charAt(column));
            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            // This is the statement that tells you exactly where the Ship is located.
            // System.out.print(" coordinates " + x + " = " + alphaCells.get(x-1));
        }
        // System.out.println("\n");
        return alphaCells;
    }
}