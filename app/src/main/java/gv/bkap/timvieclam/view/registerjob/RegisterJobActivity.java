package gv.bkap.timvieclam.view.registerjob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.utils.MyLocalDatabase;
import gv.bkap.timvieclam.presenter.registerjob.IRegisterJobPresenter;
import gv.bkap.timvieclam.presenter.registerjob.RegisterJobPresenter;
import gv.bkap.timvieclam.view.AbsCommonActivity;

public class RegisterJobActivity extends AbsCommonActivity implements IRegisterJobView {
    Spinner spinExp;
    Spinner spinSalary;
    Spinner spinSkill;
    EditText edtitle;
    EditText ednumber;
    EditText eddesc;
    EditText edrequest;
    EditText edinterest;
    EditText edcontact;
    Button btnRegisterJob;
    MyLocalDatabase mDB;
    IRegisterJobPresenter registerJobPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_job);
        registerJobPresenter = new RegisterJobPresenter(this, this);
        initComps();

    }


    private void initComps() {
        spinExp = (Spinner) findViewById(R.id.spinnerExp);
        spinSalary = (Spinner) findViewById(R.id.spinnerSalary);
        spinSkill = (Spinner) findViewById(R.id.spinnerLanguageJob);
        edtitle = findViewById(R.id.edTitle);
        ednumber = findViewById(R.id.edNumberEmployee);
        eddesc = findViewById(R.id.edDescJob);
        edrequest = findViewById(R.id.edRequestJob);
        edinterest = findViewById(R.id.edInterest);
        edcontact = findViewById(R.id.edContact);
        btnRegisterJob = findViewById(R.id.btn_registerJob);

    }

    public void onClick(View v) {
        String spinnerSkill = String.valueOf(spinSkill.getSelectedItemPosition() + 1);

        registerJobPresenter.registerJob(edtitle.getText().toString(), ednumber.getText().toString(), eddesc.getText().toString(), edrequest.getText().toString(), spinExp.getSelectedItem().toString(), spinSalary.getSelectedItem().toString(), spinnerSkill, edinterest.getText().toString(), edcontact.getText().toString());
    }

    @Override
    public void setTitleError(String message) {
        edtitle.setError(message);
    }

    @Override
    public void setNumberError(String message) {
        ednumber.setError(message);

    }

    @Override
    public void resetRegisterError() {

    }
}
