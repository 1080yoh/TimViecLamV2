package gv.bkap.timvieclam.view.login;

public interface ILoginView {
    void setUsernameError(String message);
    void setPasswordError(String message);
    void resetErrors();

    void showProgressDialog();

    void hideProgressDialog();
    void navigateToMainActivity();
    void navigateToRegisterActivity();

    void makeToast(String message);
}
