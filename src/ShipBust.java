import java.util.*;

public class ShipBust {

    // Declare and initialize the variables we’ll need
    private GameHelper helper = new GameHelper();
    private ArrayList<Ship> shipsList = new ArrayList<Ship>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        // first make some ships and give them locations
        // Make three Ship objects, give ‘em names, and stick ‘em in the ArrayList
        Ship one = new Ship();
        one.setName("Ship #1");
        Ship two = new Ship();
        two.setName("Ship #2");
        Ship three = new Ship();
        three.setName("Ship #3");
        shipsList.add(one);
        shipsList.add(two);
        shipsList.add(three);

        // Print brief instructions for user
        System.out.println("Your goal is to sink three ships.");
        System.out.println("Ship #1, Ship #2, Ship #3");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (Ship shipToSet : shipsList) {
            // Ask the helper for a Ship location (an ArrayList of Strings)
            ArrayList<String>newLocation=helper.placeShip(3);
            // Call the setter method on this Ship to give it the location you just got from the helper
            shipToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        // As long as the Ship list is NOT empty (the ! means NOT, it’s the same as (shipsList.isEmpty() == false)
        while(!shipsList.isEmpty()) {
            // Get user input
            String userGuess = helper.getUserInput("Enter a guess");
            // Call our own checkUserGuess method
            checkUserGuess(userGuess);
    }
        // Call our own finishGame method
        finishGame();
    }

    private void checkUserGuess(String userGuess) {

        // increment the number of guesses the user has made
        numOfGuesses++;
        // assume it’s a ‘miss’, unless told otherwise
        String result = "miss";
        // repeat with all Ships in the list
        for (Ship shipToTest : shipsList) {
            // ask the Ship to check the user guess, looking for a hit (or kill)
            result = shipToTest.checkYourself(userGuess);
            if (result.equals("hit")) {
                // get out of the loop early, no point in testing the others
                break;
            }
            if (result.equals("kill")) {
                // this guy’s dead, so take him out of the Ships list then get out of the loop
                shipsList.remove(shipToTest);
                break;
            }
        }
        System.out.println(result);
    }

    /*
    Print a message telling the user how he did in the game
     */
    private void finishGame() {
        System.out.println("All Ships are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println(" You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. "+ numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }

    public static void main (String[] args) {
        // create the game object
        ShipBust game = new ShipBust();
        // tell the game object to set up the game
        game.setUpGame();
        // tell the game object to start the main game play loop (keeps asking for user input and checking the guess)
        game.startPlaying();
    }
}
