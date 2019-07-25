package gv.bkap.timvieclam.model;

import android.app.Application;

import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.utils.PrefSaveProfile;

public class ApplicationContext extends Application {

    public ApplicationContext() {
    }

    public Account getAccount() {
        return new PrefSaveProfile(this).getAccount();
    }

    public void saveAccount(Account account) {
        new PrefSaveProfile(this).saveAccount(account);
    }


    public void removeAccount() {
        new PrefSaveProfile(this).removeAccount();
    }

}
