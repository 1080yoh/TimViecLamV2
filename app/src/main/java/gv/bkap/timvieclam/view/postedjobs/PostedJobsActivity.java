package gv.bkap.timvieclam.view.postedjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.PostedJobItem;
import gv.bkap.timvieclam.model.utils.IntentKeyWord;
import gv.bkap.timvieclam.presenter.postedjobs.IPostedJobsPresenter;
import gv.bkap.timvieclam.presenter.postedjobs.PostedJobsPresneter;
import gv.bkap.timvieclam.view.AbsCommonActivity;
import gv.bkap.timvieclam.view.detailjob.DetailJobActivity;

public class PostedJobsActivity extends AbsCommonActivity implements IPostedJobsView {

    IPostedJobsPresenter postedJobsPresenter;
    private ImageView ivAvatar;
    private TextView tvNameDisplayed;
    private ListView lvPostedJobs;
    private ArrayList<Object> lstPostedJobs;
    private PostedJobAdapter postedJobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_jobs);
        postedJobsPresenter = new PostedJobsPresneter(this);

        initComps();
        initData();
        addEvents();
    }


    private void initComps() {
        ivAvatar = findViewById(R.id.ivAvatar);
        tvNameDisplayed = findViewById(R.id.tvNameDisplayed);
        lvPostedJobs = findViewById(R.id.lvPostedJobs);
    }

    private void initData() {
        lstPostedJobs = new ArrayList<>();
        postedJobAdapter = new PostedJobAdapter(this, lstPostedJobs);

        lvPostedJobs.setAdapter(postedJobAdapter);

        Account account = ((ApplicationContext) getApplicationContext()).getAccount();
        Glide.with(this).load(account.getAvatar()).into(ivAvatar);
        tvNameDisplayed.setText(account.getName_displayed());

        postedJobsPresenter.loadDataPostedJobs(account.getId());
    }

    private void addEvents() {
        lvPostedJobs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lstPostedJobs.get(position) instanceof String)
                    return;

                Intent intent = new Intent(PostedJobsActivity.this, DetailJobActivity.class);
                intent.putExtra(IntentKeyWord.ID_JOB, ((PostedJobItem) lstPostedJobs.get(position)).getIdJob() + "");
                startActivity(intent);
            }
        });
    }


    @Override
    public void loadPostedJobs(ArrayList<Object> lstData) {
        if (lstData == null)
            return;
        this.lstPostedJobs.addAll(lstData);
        postedJobAdapter.notifyDataSetChanged();
    }
}
