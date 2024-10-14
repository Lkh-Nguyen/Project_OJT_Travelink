package com.travelink.Model;

public class PendingHotel {

  private int pendingHotelID;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private String address;
  private int wardID;

  public PendingHotel() {
    // Default constructor
  }

  public PendingHotel(int pendingHotelID, String name, String email, String password, 
                        String phoneNumber, String address, int wardID) {
    this.pendingHotelID = pendingHotelID;
    this.name = name;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.wardID = wardID;
  }

  // Getters and Setters for all member variables

  public int getPendingHotelID() {
    return pendingHotelID;
  }

  public void setPendingHotelID(int pendingHotelID) {
    this.pendingHotelID = pendingHotelID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getWardID() {
    return wardID;
  }

  public void setWardID(int wardID) {
    this.wardID = wardID;
  }

  @Override
  public String toString() {
    return "PendingHotel{" +
        "pendingHotelID=" + pendingHotelID +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        // Exclude password for security reasons in toString()
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        ", wardID=" + wardID +
        '}';
  }
}
