import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import static javax.swing.JOptionPane.showOptionDialog;

/**
 * This is a class for the user client, which allows for user interaction
 */
public class HearingAidClient {

    private static final int YES_NO_OPTION = 0;
    private static final int INFORMATION_MESSAGE = 1;
    /**
     * This is field stores a HearingAidCentral object.
     */
    private HearingAidCentral hearingAidCentral;
    /**
     * This field stores user input.
     */
    private UserInput input;

    /**
     * The constructor creates a HearingAidCentral object, and a UserInput object.
     * @param name
     */
    public HearingAidClient(String name) {
        hearingAidCentral = new HearingAidCentral(name);
        input = new UserInput();
    }

    /**
     * This method prints all the device information
     */
    public void printInfo() {
        ArrayList<HearingAid> hearingAidList = hearingAidCentral.getHearingAidList();
        Iterator<HearingAid> it = hearingAidCentral.getIterator();
        if(hearingAidList.size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no devices in the list");
        }
        else {
            String output = "A list of all the registered devices:" + "\n";
            while (it.hasNext()) {
                output +=it.next().returnInfo() + "\n";
            }
            JOptionPane.showMessageDialog(null, output);
        }
    }

    /**
     * This method registers a new device.
     */
    public void registerNew() {
        int id = input.intInputBetween("the ID of the device you wish to add, the ID must be a number " +
                "between 1001 and 9999",1001, 9999);
        String type = input.stringInput("the type of device you wish to add");
        boolean rented;
        String nameOfRenter = "";
        if(input.stringInput("whether the device is currently rented, type 'yes' or 'no'").equalsIgnoreCase("yes")) {
            rented = true;
            nameOfRenter = input.stringInput("the name of the person renting the device");
        }
        else {
            rented = false;
        }
        hearingAidCentral.addHearingAid(id, type, rented, nameOfRenter);
    }

    /**
     * This method rents a device.
     */
    public void rentDevice() {
        int id = input.intInputBetween("the ID of the device you wish to rent",1001, 9999);
        Iterator<HearingAid> it = hearingAidCentral.getIterator();
        boolean found = false;
        while(it.hasNext()) {
            HearingAid hearingAid = it.next();
            if(hearingAid.getId() == id) {
                if(hearingAid.getRentalStatus()) {
                    JOptionPane.showMessageDialog(null, "This device is already rented");
                    found = true;
                }
                else {
                    String nameOfRenter = input.stringInput("The name of the renter");
                    hearingAid.setRentalStatus(true);
                    hearingAid.setNameOfRenter(nameOfRenter);
                    found = true;
                    JOptionPane.showMessageDialog(null, "Device with id " + hearingAid.getId() + " and type " + hearingAid.getType()
                            + " rented to " + hearingAid.getNameOfRenter());
                }
            }
        }
        if(!found) {
            JOptionPane.showMessageDialog(null, "No device with this ID was found.");
        }
    }

    /**
     * This method returns a device.
     */
    public void returnDevice() {
        int id = input.intInputBetween("the ID of the device you wish to return", 1001, 9999);
        Iterator<HearingAid> it = hearingAidCentral.getIterator();
        boolean found = false;
        while(it.hasNext()) {
            HearingAid hearingAid = it.next();
            if(hearingAid.getId() == id) {
                if(!hearingAid.getRentalStatus()) {
                    JOptionPane.showMessageDialog(null, "This device is not rented");
                    found = true;
                }
                else {
                    hearingAid.setRentalStatus(false);
                    hearingAid.setNameOfRenter("");
                    found = true;
                    JOptionPane.showMessageDialog(null, "Device successfully returned");
                }
            }
        }
        if(!found) {
            JOptionPane.showMessageDialog(null, "There is no device with this ID");
        }
    }

    /**
     * This is the main function, which starts the application.
     * @param args
     */
    public static void main(String[] args){
        HearingAidClient client = new HearingAidClient("NTNU-central");
        HearingAidCentral ntnu = new HearingAidCentral("NTNU-central");
        String [] options = {"List All Information", "Register a new device", "Rent a device",
                "Return a device", "Quit"};
        final int LIST_ALL = 0;
        final int REGISTER_NEW_DEVICE = 1;
        final int RENT_A_DEVICE = 2;
        final int RETURN_A_DEVICE = 3;
        final int QUIT = 4;

        int choice = showOptionDialog(null, "Hearing Aid Central " + ntnu.getName() + "\nChoose a function",
                "Eksamen des 2019", YES_NO_OPTION,INFORMATION_MESSAGE, null, options, options[0]);
        while (choice != QUIT){
            switch (choice){
                case LIST_ALL:
                    client.printInfo();
                    break;
                case REGISTER_NEW_DEVICE:
                    client.registerNew();
                    break;
                case RENT_A_DEVICE:
                    client.rentDevice();
                    break;
                case RETURN_A_DEVICE:
                    client.returnDevice();
                    break;
                default: break;
            }
            choice = showOptionDialog(null, "Hearing Aid Central " + ntnu.getName() + "\nChoose a function",
                    "Eksamen des 2019", YES_NO_OPTION,INFORMATION_MESSAGE, null, options, options[0]);
        }

    }
}
