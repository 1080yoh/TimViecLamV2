package gv.bkap.timvieclam.view.main;

public interface IMainView {
    void navigateToLoginActivity();
    void navigateToInfoActivity();

    void navigateToRegisterJobActivity();
    void loadData();

    void setNavMenu(int idMenu);

    void showToast(String message);
}
