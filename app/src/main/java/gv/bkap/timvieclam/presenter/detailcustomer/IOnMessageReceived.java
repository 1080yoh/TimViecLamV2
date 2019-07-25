package gv.bkap.timvieclam.presenter.detailcustomer;

import gv.bkap.timvieclam.model.entity.MessageFromServer;

public interface IOnMessageReceived {
    void onMessageChangeInfoReceived(MessageFromServer messageFromServer);

    void onMessageChangePasswordReceived(MessageFromServer messageFromServer);
}
