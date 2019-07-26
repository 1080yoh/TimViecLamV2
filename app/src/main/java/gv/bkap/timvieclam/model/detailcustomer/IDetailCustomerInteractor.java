package gv.bkap.timvieclam.model.detailcustomer;

public interface IDetailCustomerInteractor {
    void saveInfo(int id_account, String nameDisplayed, String address, String phoneContact, String emailContact);

    void changePassword(String username, String oldPassword, String newPassword);
}
