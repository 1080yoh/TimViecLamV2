package gv.bkap.timvieclam.model.detailcustomer;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import gv.bkap.timvieclam.model.entity.MessageFromServer;
import gv.bkap.timvieclam.model.entity.MessageFromServerMapper;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.detailcustomer.IOnMessageReceived;

public class DetailCustomerInteractor implements IDetailCustomerInteractor {
    private String TAG = "DetailCustomerInteractor";
    private IOnMessageReceived iOnMessageReceived;

    public DetailCustomerInteractor(IOnMessageReceived iOnMessageReceived) {
        this.iOnMessageReceived = iOnMessageReceived;
    }

    @Override
    public void saveInfo(int id_account, String nameDisplayed, String address, String phoneContact, String emailContact) {
        ChangeInfoTask changeInfoTask = new ChangeInfoTask();
        changeInfoTask.execute(id_account + "", nameDisplayed, phoneContact, emailContact, address);
    }

    @Override
    public void changePassword(int id_account, String oldPassword, String newPassword) {

    }

    private class ChangeInfoTask extends AsyncTask<String, Void, MessageFromServer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected MessageFromServer doInBackground(String... strings) {
            if (strings.length != 5)
                return null;
            String sendData[][] = {
                    {"id_account", strings[0]},
                    {"name_displayed", strings[1]},
                    {"phone", strings[2]},
                    {"email_contact", strings[3]},
                    {"address", strings[4]}
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_CHANGE_INFO, sendData);
            Log.e(TAG, "Result: " + result);
            try {
                return MessageFromServerMapper.mapMessageFromServer(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MessageFromServer messageFromServer) {
            super.onPostExecute(messageFromServer);
            iOnMessageReceived.onMessageChangeInfoReceived(messageFromServer);
        }
    }

    private class ChangePasswordTask extends AsyncTask<String, Void, MessageFromServer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected MessageFromServer doInBackground(String... strings) {
            if (strings.length != 3)
                return null;
            String sendData[][] = {
                    {"id_account", strings[0]},
                    {"oldpassword", strings[1]},
                    {"newpassword", strings[2]},
            };

            final String result = new ServerInteractor().sendRequest(
                    1, ServerInteractor.HOSTING_API + ServerInteractor.PAGE_CHANGE_INFO, sendData);
            Log.e(TAG, "Result: " + result);
            try {
                return MessageFromServerMapper.mapMessageFromServer(result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(MessageFromServer messageFromServer) {
            super.onPostExecute(messageFromServer);
            iOnMessageReceived.onMessageChangeInfoReceived(messageFromServer);
        }
    }

}
