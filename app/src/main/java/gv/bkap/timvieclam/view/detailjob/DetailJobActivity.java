package gv.bkap.timvieclam.view.detailjob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import gv.bkap.timvieclam.R;

public class DetailJobActivity extends AppCompatActivity {
    TextView tvJobName;
    TextView tvCompanyName;
    TextView tvSalary;
    TextView tvExp;
    TextView tvLocation;
    TextView tvNumberPerson;
    TextView tvJob;
    TextView tvDesc;
    TextView tvRequest;
    TextView tvInterest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);
        initDetail();
    }

    private void initDetail() {
        tvJobName = (TextView) findViewById(R.id.tvJobName);
        tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        tvSalary = (TextView) findViewById(R.id.tvJobSalary);
        tvExp = (TextView) findViewById(R.id.tvExpJob);
        tvLocation = (TextView) findViewById(R.id.tvJobLocation);
        tvNumberPerson = (TextView) findViewById(R.id.tvNumberEmploye);
        tvJob = (TextView) findViewById(R.id.tvJob);
        tvDesc = (TextView) findViewById(R.id.tvDescribe);
        tvRequest = (TextView) findViewById(R.id.tvRequest);
        tvInterest = (TextView) findViewById(R.id.tvInterest);

    }


}
