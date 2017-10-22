package com.sample.data.migration.model;

/**
 * Created by Kalidass Mahalingam on 10/22/2017.
 */
public class DataTable {

    private String firstName;

    private String lastName;

    private long mobileNo;

    private String address;

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets mobile no.
     *
     * @return the mobile no
     */
    public long getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets mobile no.
     *
     * @param mobileNo the mobile no
     */
    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
