package gv.bkap.timvieclam.model.entity;

public class JobItem {
    private String logoLink;
    private String jobName;
    private String renterName;
    private String location;
    private String salary;
    private String timeUptoNow;

    public JobItem(String logoLink, String jobName, String renterName, String location, String salary, String timeUptoNow) {
        this.logoLink = logoLink;
        this.jobName = jobName;
        this.renterName = renterName;
        this.location = location;
        this.salary = salary;
        this.timeUptoNow = timeUptoNow;
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
}
