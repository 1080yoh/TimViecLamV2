package gv.bkap.timvieclam.view.detailjob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gv.bkap.timvieclam.R;

public class DetailJobActivity extends AppCompatActivity {
    String jobname;
    String companyname;
    String salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_job);
    }

    public void initDetail() {


    }
}
