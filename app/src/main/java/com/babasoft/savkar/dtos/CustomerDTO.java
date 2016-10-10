package com.babasoft.savkar.dtos;

/**
 * Created by s5 on 7/10/16.
 */

public class CustomerDTO {
    String customername;

    String cust_phone;

    String cust_email;

    String cust_address;

    int cust_amount;

    int cust_interest;

    String profilephoto;

    String vitnessphoto;

    String registerDate;

    byte[] profileImage;

    byte[] vitnessImage;

    public String getCustomername() {
        return customername;
    }


    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public int getCust_amount() {
        return cust_amount;
    }

    public void setCust_amount(int cust_amount) {
        this.cust_amount = cust_amount;
    }

    public int getCust_interest() {
        return cust_interest;
    }

    public void setCust_interest(int cust_interest) {
        this.cust_interest = cust_interest;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }

    public String getVitnessphoto() {
        return vitnessphoto;
    }

    public void setVitnessphoto(String vitnessphoto) {
        this.vitnessphoto = vitnessphoto;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public byte[] getVitnessImage() {
        return vitnessImage;
    }

    public void setVitnessImage(byte[] vitnessImage) {
        this.vitnessImage = vitnessImage;
    }

}
