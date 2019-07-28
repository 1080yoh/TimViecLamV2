package gv.bkap.timvieclam.model.entity;

public class Category {
    private int id;
    private String name;
    private String icon_link;

    public Category(int id, String name, String icon_link) {
        this.id = id;
        this.name = name;
        this.icon_link = icon_link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_link() {
        return icon_link;
    }

    public void setIcon_link(String icon_link) {
        this.icon_link = icon_link;
    }
}
