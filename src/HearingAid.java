/**
 * This is a class for each individual Hearing Aid.2
 */
public class HearingAid {
    /**
     * This is a field of the type int for the id of the device.
     */
    private int id;
    /**
     * This is a field of the type String for the type of the device.
     */
    private String type;
    /**
     * This is a field of the type boolean for the rental status of the device.
     */
    private boolean rentalStatus;
    /**
     * This is a field of the type String for the name of the current renter of the device.
     */
    private String nameOfRenter;

    /**
     * A constructor for the HearingAid class, which creates and object with given parameters.
     * @param id The unique ID of the device
     * @param type The type of device
     * @param rentalStatus The rental status of the device
     * @param nameOfRenter The name of the current renter.
     */
    public HearingAid(int id, String type, boolean rentalStatus, String nameOfRenter) {
        this.id = id;
        this.type = type;
        this.rentalStatus = rentalStatus;
        this.nameOfRenter = nameOfRenter;
    }

    /*
    I have made getters and setters for the rentalStatus and nameOfRenter fields, this is to achieve loose coupling
    I have also made getters for the id and type fields, i have not made setter for these fields, because they
    are not gonna be changed.
     */

    /**
     * A method which returns the id of a device
     * @return The id of the device in an int.
     */
    public int getId() {
        return id;
    }

    /**
     * A method which returns the type of device
     * @return The type of device in a String
     */
    public String getType() {
        return type;
    }

    /**
     * A method which returns the rental status of the device
     * @return The rental status of the device in a boolean.
     */
    public boolean getRentalStatus() {
        return rentalStatus;
    }

    /**
     * A method which sets the rental status
     * @param rentalStatus a boolean value.
     */
    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    /**
     * A method which returns the name of the current renter.
     * @return The name of the renter in a String.
     */
    public String getNameOfRenter() {
        return nameOfRenter;
    }

    /**
     * A method which sets the name of the renter.
     * @param nameOfRenter A String which contains the name of the renter.
     */
    public void setNameOfRenter(String nameOfRenter) {
        this.nameOfRenter = nameOfRenter;
    }

    /**
     * A method which compares the device ID to a given ID
     * @param id The given id as an int.
     * @return a boolean value which says if the devices are the same.
     */
    public boolean compareDevices(int id) {
        boolean equal = false;
        if(this.id == id) {
            equal = true;
        }
        return equal;
    }

    public String returnInfo() {
        String info = "";
        if(rentalStatus) {
            info = this.id + " " + this.type + " Rented to: " + this.nameOfRenter;
        }
        else {
            info = this.id + " " + this.type + " Available";
        }
        return info;
    }
}
