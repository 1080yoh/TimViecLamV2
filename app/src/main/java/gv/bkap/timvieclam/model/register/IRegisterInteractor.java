package gv.bkap.timvieclam.model.register;

public interface IRegisterInteractor {
    void register(String username, String password, String name_display, String email_restore);
}
