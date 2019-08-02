package gv.bkap.timvieclam.model.entity;

public class PostedJobItem {
    private int idJob;
    private int idCustomer;
    private String jobName;
    private String location;
    private int quantity;
    private String description;
    private String contact;
    private String salary;
    private String posted_date;

    public PostedJobItem(int idJob, int idCustomer, String jobName, String location, int quantity, String description, String contact, String salary, String posted_date) {
        this.idJob = idJob;
        this.idCustomer = idCustomer;
        this.jobName = jobName;
        this.location = location;
        this.quantity = quantity;
        this.description = description;
        this.contact = contact;
        this.salary = salary;
        this.posted_date = posted_date;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(String posted_date) {
        this.posted_date = posted_date;
    }
}
