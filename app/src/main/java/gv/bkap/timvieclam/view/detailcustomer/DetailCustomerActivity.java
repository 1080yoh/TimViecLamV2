package gv.bkap.timvieclam.view.detailcustomer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gv.bkap.timvieclam.R;

public class DetailCustomerActivity extends AppCompatActivity implements IDetailCustomerView {

    private TextView tvUsername;
    private EditText etNameDisplayed;
    private EditText etAddress;
    private EditText etPhoneContact;
    private EditText etEmailContact;
    private EditText etOldPassword;
    private EditText etNewPassword;
    private EditText etRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);

        initComps();
    }

    private void initComps() {
        tvUsername = findViewById(R.id.tvUsername);

        etNameDisplayed = findViewById(R.id.etNameDisplayed);
        etAddress = findViewById(R.id.etAddress);
        etPhoneContact = findViewById(R.id.etPhoneContact);
        etEmailContact = findViewById(R.id.etEmailContact);

        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        etRetypePassword = findViewById(R.id.etRetypePassword);
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

}
