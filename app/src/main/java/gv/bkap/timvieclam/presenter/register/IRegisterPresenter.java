package gv.bkap.timvieclam.presenter.register;

public interface IRegisterPresenter {
    Boolean checkRegister(String username, String name, String password,String repassword, String email);
    void AddAccount(String username,String password,String name_display,String email);
}
