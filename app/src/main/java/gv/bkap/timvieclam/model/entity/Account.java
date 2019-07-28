package gv.bkap.timvieclam.model.entity;

public class Account {
    private int id;
    private String username;
    private String password;
    private String name_displayed;
    private String email_restore;
    private String avatar;

    public Account() {
    }

    public Account(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String name_displayed, String email_restore) {
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
    }

    public Account(int id, String username, String password, String name_displayed, String email_restore, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
        this.avatar = avatar;
    }

    public Account(int id, String username, String password, String name_displayed, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.avatar = avatar;
    }

    public Account(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getName_displayed() {
        return name_displayed;
    }


    public void setName_displayed(String name_displayed) {
        this.name_displayed = name_displayed;
    }

    public String getEmail_restore() {
        return email_restore;
    }

    public void setEmail_restore(String email_restore) {
        this.email_restore = email_restore;
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
}
