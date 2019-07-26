package gv.bkap.timvieclam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONException;

import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.AccountMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.view.main.MainActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        checkAccountFromServer(((ApplicationContext) getApplicationContext()).getAccount());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToMain();
            }
        }, 500);
    }

    private void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkAccountFromServer(Account account) {
        if (account != null && account.getId() != -1) {
            LoginTask loginTask = new LoginTask();
            loginTask.execute(account.getUsername(), account.getPassword());
        }
    }

    private void validated(Account account) {
        if (account == null) {
            ((ApplicationContext) getApplicationContext()).removeAccount();
        } else {
            ((ApplicationContext) getApplicationContext()).saveAccount(account);
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
                    {"password", strings[1]}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_LOGIN, sendData);
            try {
                Log.e("debug xxx", "Result got: " + result);
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
