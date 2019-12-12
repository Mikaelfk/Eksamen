import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class administers all the hearing aids, it contains a list of the hearing aids, manages the rental status
 * of the devices, and it can add devices to the list.
 */
public class HearingAidCentral {
    /**
     * This is a field for the name of the central
     */
    private String name;

    /**
     * This is a field for the complete list of hearing aids.
     */
    private ArrayList<HearingAid> hearingAidList;

    /**
     * The constructor sets a name for the central and creates the ArrayList.
     * @param name The name of the central
     */
    public HearingAidCentral(String name) {
        this.name = name;
        hearingAidList = new ArrayList<>();
    }

    /**
     * This method adds a hearing aid to the list.
     * @param id a parameter for the device id
     * @param type a parameter for the device type
     * @param rentalStatus a parameter for the rental status of the device
     * @param nameOfRenter a parameter for the name of the renter of the device
     */
    public void addHearingAid(int id, String type, boolean rentalStatus, String nameOfRenter) {
        boolean found = false;
        Iterator<HearingAid> hearingAidIterator = hearingAidList.iterator();
        HearingAid hearingAidNew = new HearingAid(id, type, rentalStatus, nameOfRenter);
        while (hearingAidIterator.hasNext()) {
            HearingAid hearingAid = hearingAidIterator.next();
            if(hearingAid.getId() == hearingAidNew.getId()) {
                found = true;
            }
        }
        if(found) {
            JOptionPane.showMessageDialog(null, "A device with this ID already exists");
        }
        else {
            hearingAidList.add(hearingAidNew);
            JOptionPane.showMessageDialog(null, "The hearing aid has been added");
        }
    }

    /**
     * This is a method which rents a device to a person.
     * @param id The id of the device which is going to be rented
     * @param nameOfRenter The name of the renter
     */
    public void rentDevice(int id, String nameOfRenter) {
        boolean found = false;
        HearingAid hearingAid = null;
        Iterator<HearingAid> hearingAidIterator = hearingAidList.iterator();
        while (hearingAidIterator.hasNext()) {
            hearingAid = hearingAidIterator.next();
            if(hearingAid.getId() == id && !hearingAid.getRentalStatus()) {
                found = true;
            }
        }
        if(found) {
            hearingAid.setRentalStatus(true);
            hearingAid.setNameOfRenter(nameOfRenter);
        }
        else {
            JOptionPane.showMessageDialog(null, "There is no device available with this ID");
        }
    }

    /**
     * This method is for returning a device.
     * @param id the id of the device which is being returned.
     */
    public void endRent(int id) {
        boolean found = false;
        HearingAid hearingAid = null;
        Iterator<HearingAid> hearingAidIterator = hearingAidList.iterator();
        while (hearingAidIterator.hasNext()) {
            hearingAid = hearingAidIterator.next();
            if(hearingAid.getId() == id) {
                found = true;
            }
        }
        if(!found) {
            JOptionPane.showMessageDialog(null, "This device does not exist");
        }
        else if(!hearingAid.getRentalStatus()) {
            JOptionPane.showMessageDialog(null, "This device is currently not rented out.");
        }
        else {
            hearingAid.setRentalStatus(false);
        }
    }

    /**
     * This method returns an ArrayList with all the hearing aids in the list.
     * @return An ArrayList which consists of HearingAid objects.
     */
    public ArrayList<HearingAid> getHearingAidList() {
        return this.hearingAidList;
    }

    /**
     * This method returns all the hearing aids which are available of a specific type.
     * @param type the specific type of hearing aid.
     * @return An ArrayList with all the HearingAid objects of the given type.
     */
    public ArrayList<HearingAid> getType(String type) {
        ArrayList<HearingAid> foundHearingAids = new ArrayList<>();
        Iterator<HearingAid> hearingAidIterator = hearingAidList.iterator();
        HearingAid hearingAid = null;
        boolean found = false;
        while (hearingAidIterator.hasNext()) {
            hearingAid = hearingAidIterator.next();
            if(hearingAid.getType().equalsIgnoreCase(type)) {
                foundHearingAids.add(hearingAid);
            }
        }
        return foundHearingAids;
    }

    /**
     * This method returns an Iterator.
     * @return An iterator of the hearingAidList ArrayList.
     */
    public Iterator<HearingAid> getIterator() {
        return hearingAidList.iterator();
    }

    /**
     * This method returns the name of the central.
     * @return The name of the central in a String.
     */
    public String getName() {
        return name;
    }
}
