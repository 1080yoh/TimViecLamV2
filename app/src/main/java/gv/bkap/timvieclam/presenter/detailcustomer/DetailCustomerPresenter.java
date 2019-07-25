package gv.bkap.timvieclam.presenter.detailcustomer;

import gv.bkap.timvieclam.model.detailcustomer.DetailCustomerInteractor;
import gv.bkap.timvieclam.model.detailcustomer.IDetailCustomerInteractor;
import gv.bkap.timvieclam.model.entity.MessageFromServer;
import gv.bkap.timvieclam.view.detailcustomer.IDetailCustomerView;

public class DetailCustomerPresenter implements IDetailCustomerPresenter, IOnMessageReceived {

    IDetailCustomerView iDetailView;
    IDetailCustomerInteractor iDetailInteractor;
    MessageFromServer messageFromServer;

    public DetailCustomerPresenter(IDetailCustomerView iDetailCustomerView) {
        this.iDetailView = iDetailCustomerView;
        iDetailInteractor = new DetailCustomerInteractor(this);
    }

    @Override
    public void changeInfo(int id_account, String nameDisplayed, String address, String phoneContact, String emailContact) {
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
        if (!error)
            iDetailInteractor.saveInfo(id_account, nameDisplayed, address, phoneContact, emailContact);
    }

    @Override
    public void changePassword(int id_account, String oldPassword, String newPassword, String retypePassword) {
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
        if (!error)
            iDetailInteractor.changePassword(id_account, oldPassword, newPassword);
    }

    @Override
    public void onMessageChangeInfoReceived(MessageFromServer messageFromServer) {
        if (messageFromServer.getId() == 100)
            iDetailView.showToast("Đổi thông tin thành công!");
        else if (messageFromServer.getId() == 101)
            iDetailView.showToast("Đổi thông tin thất bại!");
        else
            iDetailView.showToast("Đã xảy ra lỗi!");
    }

    @Override
    public void onMessageChangePasswordReceived(MessageFromServer messageFromServer) {
        if (messageFromServer.getId() == 100) {
            iDetailView.showToast("Đổi mật khẩu thành công!");
            iDetailView.clearPasswordText();
        } else if (messageFromServer.getId() == 101) {
            iDetailView.showToast("Đổi mật khẩu thất bại!");
            iDetailView.setOldPasswordError("Mật khẩu không chính xác!");
        } else
            iDetailView.showToast("Đã xảy ra lỗi!");
    }
}
