package gv.bkap.timvieclam.presenter.detailcustomer;

public interface IDetailCustomerPresenter {
    void changeInfo(int id_account, String nameDisplayed, String address, String phoneContact, String emailContact);

    void changePassword(String username, String oldPassword, String newPassword, String retypePassword);
}
