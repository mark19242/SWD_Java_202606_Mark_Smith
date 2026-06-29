package org.example;

public class Patient {

    // TODO: COMPLETE: Consider breaking Address Attrib into a separate class.
    // TODO: Review use of default CTOR and full Setter implementation

    private String patientId;
    private String patientFirstName;
    private String patientLastName;

    // Composition: Patient has an Address
    private Address patientAddress = new Address();

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Address getPatientAddress() {
        return this.patientAddress;
    }

    public void setPatientAddress(Address address) {
        this.patientAddress = address;
    }

    // TODO: !! -> Consider deprecating these methods pending review of any
    // consumers of this class. Potentially violates SRP.

    public String getPatientAddress1() {
        return this.patientAddress.getAddress1();
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress.setAddress1(patientAddress1);
    }

    public String getPatientAddress2() {
        return this.patientAddress.getAddress2();
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress.setAddress2(patientAddress2);
    }

    public String getPatientCity() {
        return this.patientAddress.getCity();
    }

    public void setPatientCity(String patientCity) {
        this.patientAddress.setCity(patientCity);
    }

    public String getPatientState() {
        return this.patientAddress.getState();
    }

    public void setPatientState(String patientState) {
        this.patientAddress.setState(patientState);
    }

    public String getPatientZip() {
        return this.patientAddress.getZip();
    }

    public void setPatientZip(String patientZip) {
        this.patientAddress.setZip(patientZip);
    }
}