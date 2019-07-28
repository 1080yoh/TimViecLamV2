package gv.bkap.timvieclam.view.detailcustomer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.presenter.detailcustomer.DetailCustomerPresenter;
import gv.bkap.timvieclam.presenter.detailcustomer.IDetailCustomerPresenter;
import gv.bkap.timvieclam.view.AbsCommonActivity;
import gv.bkap.timvieclam.view.dialog.ProgressDialog;

public class DetailCustomerActivity extends AbsCommonActivity implements IDetailCustomerView, View.OnClickListener {

    private ImageView ivAvatar;
    private TextView tvUsername;
    private EditText etNameDisplayed;
    private EditText etAddress;
    private EditText etPhoneContact;
    private EditText etEmailContact;

    private Button btChangeInfo;

    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etRetypePassword;

    private Button btChanPassword;

    private IDetailCustomerPresenter detailCustomerPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);
        if (((ApplicationContext) getApplicationContext()).getAccount().getId() == -1)
            finish();
        initComps();
        setData();
        addEvents();
        detailCustomerPresenter = new DetailCustomerPresenter(this);
    }


    private void initComps() {
        ivAvatar = findViewById(R.id.ivAvatar);
        tvUsername = findViewById(R.id.tvUsername);

        etNameDisplayed = findViewById(R.id.etNameDisplayed);
        etAddress = findViewById(R.id.etAddress);
        etPhoneContact = findViewById(R.id.etPhoneContact);
        etEmailContact = findViewById(R.id.etEmailContact);
        btChangeInfo = findViewById(R.id.btnUpdateInfo);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etRetypePassword = findViewById(R.id.etRetypePassword);
        btChanPassword = findViewById(R.id.btnChangePassword);

        progressDialog = new ProgressDialog(this);
    }


    private void setData() {
        Account account = ((ApplicationContext) getApplicationContext()).getAccount();
        tvUsername.setText(account.getUsername());
        etAddress.setText(account.getAddress());
        etEmailContact.setText(account.getEmailContact());
        etPhoneContact.setText(account.getPhone());
        etNameDisplayed.setText(account.getName_displayed());
        Log.e("DEbug xxx", "phone=" + account.getPhone());
        Glide.with(this).load(account.getAvatar()).into(ivAvatar);
    }

    private void addEvents() {
        btChangeInfo.setOnClickListener(this);
        btChanPassword.setOnClickListener(this);
    }

    @Override
    public void setUsername(String text) {
        tvUsername.setText(text);
    }

    @Override
    public void setNameDisplayed(String text) {
        etNameDisplayed.setText(text);
    }

    @Override
    public void setPhoneContact(String text) {
        etPhoneContact.setText(text);
    }

    @Override
    public void setEmailContact(String text) {
        etEmailContact.setText(text);
    }

    @Override
    public void setAddress(String text) {
        etAddress.setText(text);
    }

    @Override
    public void resetPasswordsErrors() {
        etOldPassword.setError("");
        etNewPassword.setError("");
        etRetypePassword.setError("");
    }

    @Override
    public void resetInfoErrors() {
        etNameDisplayed.setError("");
        etEmailContact.setError("");
        etPhoneContact.setError("");
        etAddress.setError("");
    }

    @Override
    public void setNameDisplayedError(String message) {
        etNameDisplayed.setError(message);
    }

    @Override
    public void setAddressError(String message) {
        etAddress.setError(message);
    }

    @Override
    public void setPhoneContactError(String message) {
        etPhoneContact.setError(message);
    }

    @Override
    public void setEmailContactError(String message) {
        etEmailContact.setError(message);
    }

    @Override
    public void setOldPasswordError(String message) {
        etOldPassword.setError(message);
    }

    @Override
    public void setNewPasswordError(String message) {
        etNewPassword.setError(message);
    }

    @Override
    public void setRetypePasswordError(String message) {
        etRetypePassword.setError(message);
    }

    @Override
    public void clearPasswordText() {
        etNewPassword.setText("");
        etRetypePassword.setText("");
        etOldPassword.setText("");
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Account account = ((ApplicationContext) getApplicationContext()).getAccount();
        switch (id) {
            case R.id.btnUpdateInfo:
                String nameDisplayed, email, address, phoneContact;
                nameDisplayed = etNameDisplayed.getText().toString();
                email = etEmailContact.getText().toString();
                address = etAddress.getText().toString();
                phoneContact = etPhoneContact.getText().toString();
                detailCustomerPresenter.changeInfo(account.getId(), nameDisplayed, address, phoneContact, email);

            case R.id.btnChangePassword:
                String username, oldPassword, newPassword, retypePassword;
                username = account.getUsername();
                oldPassword = etOldPassword.getText().toString();
                newPassword = etNewPassword.getText().toString();
                retypePassword = etRetypePassword.getText().toString();
                detailCustomerPresenter.changePassword(username, oldPassword, newPassword, retypePassword);
            default:
                break;
        }
    }
}
