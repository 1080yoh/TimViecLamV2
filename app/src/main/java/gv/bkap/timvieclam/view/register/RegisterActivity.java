package gv.bkap.timvieclam.view.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.presenter.register.IRegisterPresenter;
import gv.bkap.timvieclam.presenter.register.RegisterPresenter;
import gv.bkap.timvieclam.view.AbsCommonActivity;

public class RegisterActivity extends AbsCommonActivity implements IRegisterView, View.OnClickListener {
    EditText edUsername;
    EditText edPassword;
    EditText edRePassword;
    EditText edName;
    EditText edEmail;
    TextView tvUsernameError;
    TextView tvPasswordError;
    TextView tvRePasswordError;
    TextView tvNameError;
    TextView tvEmailError;
    Button btnRegister;
    IRegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initRegister();
        registerPresenter = new RegisterPresenter(this);
        btnRegister.setOnClickListener(this);


    }

    public void initRegister() {
        edUsername = findViewById(R.id.edUsername);
        edName = findViewById(R.id.edName);
        edPassword = findViewById(R.id.edPassword);
        edRePassword = findViewById(R.id.edRePassword);
        edEmail = findViewById(R.id.edEmail);
        tvUsernameError = findViewById(R.id.tvUsernameError);
        tvPasswordError = findViewById(R.id.tvPasswordError);
        tvRePasswordError = findViewById(R.id.tvRePasswordError);
        tvNameError = findViewById(R.id.tvNameError);
        tvEmailError = findViewById(R.id.tvEmailError);
        btnRegister = findViewById(R.id.btn_regisAccount);

    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void registerFail() {
        Toast.makeText(this, "Đăng kí thất bại", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setError() {

        // check username error
        int countSpaceUsername = 0;
        char charUsername;
        String username = edUsername.getText().toString();
        username.trim();
        if (username.length() == 0) {
            tvUsernameError.setText("Tài khoản không được bỏ trống");
        } else {
            for (int i = 0; i < username.length(); i++) {
                charUsername = username.charAt(i);
                if (Character.isSpaceChar(charUsername)) {
                    countSpaceUsername++;
                }
            }
            if (countSpaceUsername > 0) {
                tvUsernameError.setText("Tên đăng nhập không hợp lệ");
            } else {
                tvUsernameError.setText("");
            }
        }


        //check password error
        int countSpacePassword = 0;
        char charPassword;
        String password = edPassword.getText().toString();
        password.trim();
        if (password.length() == 0) {
            tvPasswordError.setText("Mật khẩu không được bỏ trống");
        } else {
            for (int i = 0; i < password.length(); i++) {
                charPassword = password.charAt(i);
                if (Character.isSpaceChar(charPassword)) {
                    countSpacePassword++;
                }
            }
            if (countSpacePassword > 0) {
                tvPasswordError.setText("Mật khẩu không được chứa khoảng trắng");
            } else {
                tvPasswordError.setText("");
            }
        }


        //check repassword error
//        String password = edPassword.getText().toString();
        String rePassword = edRePassword.getText().toString();
        rePassword.trim();
        if (password.length() == 0) {
            tvRePasswordError.setText("Mật khẩu không được bỏ trống");
        } else if (!rePassword.equals(password)) {
            tvRePasswordError.setText("Mật khẩu không trùng khớp");
        } else {
            tvRePasswordError.setText("");
        }


        //check name_display
        String name = edName.getText().toString();
        name = name.trim();
        if (name.length() == 0) {
            tvNameError.setText("Tên không được bỏ trống");
        } else {
            tvNameError.setText("");
        }
        while (name.indexOf("  ") != -1) {
            name = name.replaceAll("  ", " ");
        }
        name = name.toUpperCase();
        edName.setText(name);


        //check emailerror
        int countSpaceEmail = 0;
        char charEmail;
        String email = edEmail.getText().toString();
        email = email.trim();
        if (email.length() == 0) {
            tvEmailError.setText("Email không hợp lệ");
        } else {
            for (int i = 0; i < email.length(); i++) {
                charEmail = email.charAt(i);
                if (Character.isSpaceChar(charEmail)) {
                    countSpaceEmail++;
                }
            }
            if (countSpaceEmail > 0) {
                tvEmailError.setText("Email không được chứa khoảng trắng");
            } else {
                tvEmailError.setText("");
            }
        }

    }

    @Override
    public void accoutnAlreadyExits() {
        Toast.makeText(this, "Lỗi! Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
        edUsername.setError("Tài khoản đã tồn tại");
    }


    @Override
    public void onClick(View v) {
        setError();
        String usernameError = tvUsernameError.getText().toString();
        String passwordError = tvPasswordError.getText().toString();
        String repasswordError = tvRePasswordError.getText().toString();
        String nameError = tvNameError.getText().toString();
        String emailError = tvEmailError.getText().toString();

        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String name_display = edName.getText().toString();
        String email = edEmail.getText().toString();
        Boolean check = registerPresenter.checkRegister(usernameError, nameError, passwordError, repasswordError, emailError);
        if (check == false) {
            registerPresenter.AddAccount(username, password, name_display, email);
        } else {
            registerFail();
        }
    }
}
