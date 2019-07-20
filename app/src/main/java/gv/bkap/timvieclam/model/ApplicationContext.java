package gv.bkap.timvieclam.model;

import android.app.Application;

import gv.bkap.timvieclam.model.entity.Account;

public class ApplicationContext extends Application {

    Account account;

    public ApplicationContext() {
        account = null;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
