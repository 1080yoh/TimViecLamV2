package gv.bkap.timvieclam.model.entity;

public class Job {
    int idJob;
    int idCustomer;
    int idlistWork;
    String nameJob;
    String RequestJob;
    String SalaryJob;
    String describe;
    int number;
    String interest;

    public Job(int idJob, int idCustomer, int idlistWork, String nameJob, String requestJob, String salaryJob, String describe, int number) {
        this.idJob = idJob;
        this.idCustomer = idCustomer;
        this.idlistWork = idlistWork;
        this.nameJob = nameJob;
        RequestJob = requestJob;
        SalaryJob = salaryJob;
        this.describe = describe;
        this.number = number;
    }

    public Job(int idCustomer, int idlistWork, String nameJob, String requestJob, String salaryJob, String describe, int number) {
        this.idCustomer = idCustomer;
        this.idlistWork = idlistWork;
        this.nameJob = nameJob;
        RequestJob = requestJob;
        SalaryJob = salaryJob;
        this.describe = describe;
        this.number = number;
    }

    public Job(int idJob, int idCustomer, int idlistWork, String nameJob, String requestJob, String salaryJob, String describe, int number, String interest) {
        this.idJob = idJob;
        this.idCustomer = idCustomer;
        this.idlistWork = idlistWork;
        this.nameJob = nameJob;
        RequestJob = requestJob;
        SalaryJob = salaryJob;
        this.describe = describe;
        this.number = number;
        this.interest = interest;
    }

    public int getIdJob() {
        return idJob;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
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

    public int getIdlistWork() {
        return idlistWork;
    }

    public void setIdlistWork(int idlistWork) {
        this.idlistWork = idlistWork;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public String getRequestJob() {
        return RequestJob;
    }

    public void setRequestJob(String requestJob) {
        RequestJob = requestJob;
    }

    public String getSalaryJob() {
        return SalaryJob;
    }

    public void setSalaryJob(String salaryJob) {
        SalaryJob = salaryJob;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

