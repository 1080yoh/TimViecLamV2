package gv.bkap.timvieclam.model.register;

import android.os.AsyncTask;
import android.util.Log;

import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.model.utils.MD5Encoder;

public class RegisterInteractor implements IRegisterInteractor{
    private final String TAG = "RegisterInteractor";

    @Override
    public void register(String username, String password, String name_display, String email_restore) {

        RegisterAccount registerAccount = new RegisterAccount();
        registerAccount.execute(new String[]{username,password,name_display,email_restore});
    }

    private class RegisterAccount extends AsyncTask<String, Void,Account> {


        @Override
        protected Account doInBackground(String... strings) {
            if (strings.length != 4)
                return null;
            String sendData[][] = {
                    {"username", strings[0]},
                    {"password", MD5Encoder.encode(strings[1])},
                    {"name_display",strings[2]},
                    {"email",strings[3]}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_INSERT, sendData);
            Log.e(TAG, "Result: " + result);

            return null;
        }
    }
}
