package gv.bkap.timvieclam.model.entity;

public class Account {
    private int id;
    private String username;
    private String password;
    private String repassword;
    private String name_displayed;
    private String email_restore;
    private int type;

    public Account() {
    }

    public Account(String username, String password, String name_displayed, String email_restore, int type) {
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
        this.type = type;
    }

    public Account(int id, String username, String password, String name_displayed, String email_restore, int type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
        this.type = type;
    }

    public Account(String username, String password, String name_displayed, String email_restore) {
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
    }

    public Account(int id, String username, String password, String name_displayed, String email_restore) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name_displayed = name_displayed;
        this.email_restore = email_restore;
    }

    public String getName_displayed() {
        return name_displayed;
    }

    public void setName_displayed(String name_displayed) {
        this.name_displayed = name_displayed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
