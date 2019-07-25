package gv.bkap.timvieclam.model.detailcustomer;

public interface IDetailCustomerInteractor {
    void saveInfo(int id_account, String nameDisplayed, String address, String phoneContact, String emailContact);

    void changePassword(int id_account, String oldPassword, String newPassword);
}
