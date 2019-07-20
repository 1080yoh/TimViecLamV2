package gv.bkap.timvieclam.model.login;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.AccountMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.model.utils.MD5Encoder;
import gv.bkap.timvieclam.presenter.login.IOnLoginValidateListener;

public class LoginInteractor implements ILoginInteractor {

    private final String TAG = "LoginInteractor";

    IOnLoginValidateListener onLoginValidateListener;
    Context context;

    @Override
    public void login(String username, String password, IOnLoginValidateListener onLoginValidateListener, Context context) {

        this.onLoginValidateListener = onLoginValidateListener;
        this.context = context;

        LoginTask loginTask = new LoginTask();
        loginTask.execute(new String[]{username, password});

    }

    private void validated(Account account) {
        Boolean error = false;

        if (account == null) {
            Log.e("debug xxx", "Login unsuccessfully");
            onLoginValidateListener.onUsernameError(context.getString(R.string.error_username_password_wrong));
            error = true;
        }

        if (!error) {
            onLoginValidateListener.onLoginSuccess(account);
            Log.e("debug xxx", "Login successfully");
        }
    }

    private class LoginTask extends AsyncTask<String, Void, Account> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Account doInBackground(String... strings) {
            if (strings.length != 2)
                return null;
            String sendData[][] = {
                    {"username", strings[0]},
                    {"password", MD5Encoder.encode(strings[1])}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOGIN, sendData);
            Log.e(TAG, "Result: " + result);
            try {
                Log.e("debug xxx", "Result got: "+result);
                return AccountMapper.getAccount(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Account account) {
            validated(account);
        }
    }
}
