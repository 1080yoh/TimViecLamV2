package gv.bkap.timvieclam.view.main;

public interface IMainView {
    void navigateToLoginActivity();

    void navigateToDetailCustomerActivity();

    void loadData();

    void setNavMenu(int idMenu);

    void showToast(String message);
}
