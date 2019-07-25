package gv.bkap.timvieclam.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.utils.MyLocalDatabase;
import gv.bkap.timvieclam.presenter.login.ILoginPresenter;
import gv.bkap.timvieclam.presenter.login.LoginPresenter;
import gv.bkap.timvieclam.view.AbsCommonActivity;
import gv.bkap.timvieclam.view.dialog.ProgressDialog;
import gv.bkap.timvieclam.view.main.MainActivity;
import gv.bkap.timvieclam.view.register.RegisterActivity;

public class LoginActivity extends AbsCommonActivity implements ILoginView, View.OnClickListener {

    ILoginPresenter loginPresenter;

    AutoCompleteTextView etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;
    ProgressDialog progressDialog;

    MyLocalDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginPresenter = new LoginPresenter(this, this);
        initComps();
        initData();
        addEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        etPassword.setText("");
        etPassword.setError(null);
        etUsername.setText("");
        etUsername.setError(null);
    }

    private void addEvents() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void initData() {

        progressDialog = new ProgressDialog(this);

        // Database luu cac tai khoan da dang nhap
        mDB = new MyLocalDatabase(this);
        ArrayList<String> lstUsernameSaved = mDB.getUsernameSaved();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lstUsernameSaved);
        etUsername.setAdapter(adapter);

    }

    private void initComps() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
    }

    @Override
    public void setUsernameError(String message) {
        etUsername.setError(message);
    }

    @Override
    public void setPasswordError(String message) {
        etPassword.setError(message);
    }

    @Override
    public void resetErrors() {
        etUsername.setError(null);
        etUsername.setError(null);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegisterActivity() {
        Intent register = new Intent(this, RegisterActivity.class);
        startActivity(register);
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                loginPresenter.login(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btn_register:
                navigateToRegisterActivity();
                break;
        }
    }

}
