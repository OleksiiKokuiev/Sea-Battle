import java.util.*;

public class Ship {
    private ArrayList<String> locationCells;
    private String name;

    /*
    A setter method that updates the Ship’s location. (Random location provided by the GameHelper placeShip( ) method.)
     */
    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    /*
    Basic setter method
     */
    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        // The ArrayList indexOf( ) method in action!
        // If the user guess is one of the entries in the ArrayList, indexOf( ) will return its ArrayList location.
        // If not, indexOf( ) will return -1.
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            // Using ArrayList’s remove( ) method to delete an entry
            locationCells.remove(index);
            // Using the isEmpty( ) method to see if all of the locations have been guessed
            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("Ouch! You sunk " + name + " : ( ");
            } else {
                result = "hit";
            }
        }
        // Return: ‘miss’ or ‘hit’ or ‘kill’
        return result;
    }
}
