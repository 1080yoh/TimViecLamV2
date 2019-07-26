package gv.bkap.timvieclam.presenter.detailcustomer;

import android.os.Handler;

import gv.bkap.timvieclam.model.detailcustomer.DetailCustomerInteractor;
import gv.bkap.timvieclam.model.detailcustomer.IDetailCustomerInteractor;
import gv.bkap.timvieclam.model.entity.MessageFromServer;
import gv.bkap.timvieclam.view.detailcustomer.IDetailCustomerView;

public class DetailCustomerPresenter implements IDetailCustomerPresenter, IOnMessageReceived {

    IDetailCustomerView iDetailView;
    IDetailCustomerInteractor iDetailInteractor;

    public DetailCustomerPresenter(IDetailCustomerView iDetailCustomerView) {
        this.iDetailView = iDetailCustomerView;
        iDetailInteractor = new DetailCustomerInteractor(this);
    }

    @Override
    public void changeInfo(final int id_account, final String nameDisplayed, final String address, final String phoneContact, final String emailContact) {
        boolean error = false;
        if (nameDisplayed.trim().length() == 0) {
            iDetailView.setNameDisplayedError("");
            error = true;
        }
        if (address.trim().length() == 0) {
            iDetailView.setAddressError("");
            error = true;
        }
        if (phoneContact.trim().length() == 0) {
            iDetailView.setPhoneContactError("");
            error = true;
        }
        if (emailContact.trim().length() == 0) {
            iDetailView.setEmailContactError("");
            error = true;
        }
        if (!error) {
            iDetailView.showProgressDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    iDetailInteractor.saveInfo(id_account, nameDisplayed, address, phoneContact, emailContact);
                }
            }, 1500);
        }
    }

    @Override
    public void changePassword(final String username, final String oldPassword, final String newPassword, String retypePassword) {
        boolean error = false;
        if (oldPassword.length() == 0) {
            error = true;
            iDetailView.setOldPasswordError("Mật khẩu cũ không được để trống");
        }
        if (newPassword.length() == 0) {
            error = true;
            iDetailView.setNewPasswordError("Mật khẩu mới không được để trống!");
        }
        if (!newPassword.equals(retypePassword)) {
            error = true;
            iDetailView.setRetypePasswordError("Mật khẩu không trùng khớp!");
        }
        if (!error) {
            iDetailView.showProgressDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    iDetailInteractor.changePassword(username, oldPassword, newPassword);
                }
            }, 1500);
        }
    }

    @Override
    public void onMessageChangeInfoReceived(MessageFromServer messageFromServer) {
        iDetailView.dismissProgressDialog();
        if (messageFromServer != null && messageFromServer.getId() == 100)
            iDetailView.showToast("Đổi thông tin thành công!");
        else if (messageFromServer != null && messageFromServer.getId() == 101)
            iDetailView.showToast("Đổi thông tin thất bại!");
        else
            iDetailView.showToast("Hệ thống đang bảo trì!");
    }

    @Override
    public void onMessageChangePasswordReceived(MessageFromServer messageFromServer) {
        iDetailView.dismissProgressDialog();
        if (messageFromServer == null) {
            iDetailView.showToast("Đổi mật khẩu thất bại!");
            iDetailView.setOldPasswordError("Mật khẩu không chính xác");
        } else if (messageFromServer.getId() == 100) {
            iDetailView.showToast("Đổi mật khẩu thành công!");
            iDetailView.clearPasswordText();
        } else if (messageFromServer.getId() == 101) {
            iDetailView.showToast("Đổi mật khẩu thất bại!");
            iDetailView.setOldPasswordError("Mật khẩu không chính xác!");
        } else
            iDetailView.showToast("Hệ thống đang bảo trì!");
    }
}