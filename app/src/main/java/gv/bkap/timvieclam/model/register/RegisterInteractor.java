package gv.bkap.timvieclam.model.register;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.AccountMapper;
import gv.bkap.timvieclam.model.entity.MessageFromServer;
import gv.bkap.timvieclam.model.entity.MessageFromServerMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.model.utils.MD5Encoder;
import gv.bkap.timvieclam.presenter.register.RegisterPresenter;

public class RegisterInteractor implements IRegisterInteractor {
    private String username;
    private String password;

    private final String TAG = "RegisterInteractor";
    private RegisterPresenter registerPresenter;

    public RegisterInteractor(RegisterPresenter registerPresenter) {
        this.registerPresenter = registerPresenter;
    }

    @Override
    public void register(String username, String password, String name_display, String email_restore) {
        this.username = username;
        this.password = password;
        RegisterAccount registerAccount = new RegisterAccount();
        registerAccount.execute(new String[]{username, password, name_display, email_restore});

    }

    private class RegisterAccount extends AsyncTask<String, Void, MessageFromServer> {

        @Override
        protected MessageFromServer doInBackground(String... strings) {
            if (strings.length != 4)
                return null;
            String sendData[][] = {
                    {"username", strings[0]},
                    {"password", MD5Encoder.encode(strings[1])},
                    {"name_display", strings[2]},
                    {"email", strings[3]}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_INSERT, sendData);
            Log.e(TAG, "Result: " + result);

            try {
                MessageFromServer messageFromServer = MessageFromServerMapper.mapMessageFromServer(result);
                return messageFromServer;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(MessageFromServer messageFromServer) {
            super.onPostExecute(messageFromServer);
            if (messageFromServer.getId() == 100) {
                registerPresenter.onRegisterSuccessfully();
                getAccount getAccount = new getAccount();
                getAccount.execute(username, password);
            } else
                registerPresenter.onRegisterFailure();
        }
    }


    private class getAccount extends AsyncTask<String, Void, Account> {

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
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_GET_SIMPLE_ACCOUNT, sendData);
            Log.e(TAG, "Result: " + result);
            try {
                Log.e("debug xxx", "Result got: " + result);
                return AccountMapper.getSimpleAccount(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Account account) {
            AddDefaultCustomer addDefaultCustomer = new AddDefaultCustomer();
            addDefaultCustomer.execute(account.getId() + "");
        }
    }

    private class AddDefaultCustomer extends AsyncTask<String, Void, MessageFromServer> {

        @Override
        protected MessageFromServer doInBackground(String... strings) {
            if (strings.length != 1)
                return null;
            String sendData[][] = {
                    {"id_account", strings[0]},
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_ADD_DEFAULT_CUSTOMER, sendData);
            Log.e(TAG, "Result: " + result);

            try {
                MessageFromServer messageFromServer = MessageFromServerMapper.mapMessageFromServer(result);
                return messageFromServer;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
