package gv.bkap.timvieclam.view.detailcustomer;

public interface IDetailCustomerView {

    void setUsername(String text);

    void setNameDisplayed(String text);

    void setPhoneContact(String text);

    void setEmailContact(String text);

    void setAddress(String text);

    void resetPasswordsErrors();

    void resetInfoErrors();

    void setNameDisplayedError(String message);

    void setAddressError(String message);

    void setPhoneContactError(String message);

    void setEmailContactError(String message);

    void setOldPasswordError(String message);

    void setNewPasswordError(String message);

    void setRetypePasswordError(String message);

    void clearPasswordText();

    void showToast(String message);

    void showProgressDialog();

    void dismissProgressDialog();
}
