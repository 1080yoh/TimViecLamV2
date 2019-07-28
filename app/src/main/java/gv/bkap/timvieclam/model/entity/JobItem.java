package gv.bkap.timvieclam.model.entity;

public class JobItem {
    private int id;
    private String logoLink;
    private String jobName;
    private String renterName;
    private String location;
    private String salary;
    private String timeUptoNow;
    private int id_category;

    public JobItem(int id, String logoLink, String jobName, String renterName, String location, String salary, String timeUptoNow, int id_category) {
        this.id = id;
        this.logoLink = logoLink;
        this.jobName = jobName;
        this.renterName = renterName;
        this.location = location;
        this.salary = salary;
        this.timeUptoNow = timeUptoNow;
        this.id_category = id_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTimeUptoNow() {
        return timeUptoNow;
    }

    public void setTimeUptoNow(String timeUptoNow) {
        this.timeUptoNow = timeUptoNow;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}
