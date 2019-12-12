import javax.swing.*;

public class UserInput {

    /**
     * This method takes a user input as String and checks if it's empty. If its empty the user has to
     * retype the input.
     * @return The string which the user typed in.
     */
    public String stringInput(String text) {
        String input = "";
        boolean valid = false;
        do {
            try {
                JFrame frame = new JFrame("InputDialog Example #1");
                input = JOptionPane.showInputDialog(frame, "Type in " + text);
                ;
                if (input.equals("")) {
                    JOptionPane.showMessageDialog(null, "Input cannot be empty, please try again");
                } else {
                    valid = true;
                }
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "This is not a valid String");
            }
        }while(!valid);

        return input;
    }

    /**
     * This method takes a user input as String and converts it to int. If it cant convert the String to int
     * the user has to retype the input.
     * @return the input as an int.
     */
    public int intInput(String text) {
        boolean done = false;
        int input = 0;
        do {
            try {
                JFrame frame = new JFrame();
                input = Integer.parseInt(JOptionPane.showInputDialog(frame, "Type in "+ text));
                done = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "This is not a valid number");
            }
        } while (!done);
        return input;
    }

    /**
     * This method uses the intInput() method but also checks if the input is between two given numbers
     * @param min the minimum number
     * @param max the maximum number
     * @return the input as an int.
     */
    public int intInputBetween(String text, int min, int max) {
        int input = 0;
        boolean done = false;
        do {
            input = intInput(text);
            if (input < min || input > max) {
                JOptionPane.showMessageDialog(null, "Input needs to be between " + min + " and " + max + ".");
            }
            else {
                done = true;
            }
        }while(!done);
        return input;
    }
}

