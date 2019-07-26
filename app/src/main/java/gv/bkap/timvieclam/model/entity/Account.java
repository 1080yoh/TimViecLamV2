package gv.bkap.timvieclam.model.entity;

public class Account {
    private int id;
    private String username;
    private String password;
    private String name_displayed;
    private String emailContact;
    private String address;
    private String avatar;
    private String phone;


    public Account() {
    }

    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account(int id, String username, String password, String name_displayed, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.avatar = avatar;
    }

    public Account(int id, String username, String password, String name_displayed, String emailContact, String address, String avatar, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.emailContact = emailContact;
        this.address = address;
        this.avatar = avatar;
        this.phone = phone;
    }

    public String getName_displayed() {
        return name_displayed;
    }

    public void setName_displayed(String name_displayed) {
        this.name_displayed = name_displayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
