package gv.bkap.timvieclam.view.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.presenter.main.IMainPresenter;
import gv.bkap.timvieclam.presenter.main.MainPresenter;
import gv.bkap.timvieclam.view.AbsActivityHasNavDrawable;
import gv.bkap.timvieclam.view.category.CategoryLanguage;
import gv.bkap.timvieclam.view.login.LoginActivity;
import gv.bkap.timvieclam.view.myinfo.MyInfoActivity;

public class MainActivity extends AbsActivityHasNavDrawable implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    Account account;
    private IMainPresenter mainPresenter;
    private TextView tvNavName;
    private TextView tvNavUsername;
    private ImageView ivNavAvatar;
    private ImageButton IBtnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNavDrawable();
        mainPresenter = new MainPresenter(this, getApplicationContext());
        initComps();
        initData();
        IBtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryLanguage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        mainPresenter.processOptionItemClick(id);
        return true;
    }

    @Override
    public void navigateToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        TaskOpenActivity taskOpenActivity = new TaskOpenActivity();
        taskOpenActivity.execute(intent);
    }


    @Override
    public void navigateToInfoActivity() {
        Intent intent = new Intent(this, MyInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mainPresenter.processNavMenuClick(menuItem.getItemId());

        // Chọn xong thì phải đóng lại
        closeNavDrawable();
        return true;
    }

    private void initComps() {
        tvNavName = header.findViewById(R.id.tvNavName);
        tvNavUsername = header.findViewById(R.id.tvNavUsername);
        ivNavAvatar = header.findViewById(R.id.ivNavAvatar);
        IBtnCategory = findViewById(R.id.IBtn_danhmuc);
//        tvMain = findViewById(R.id.tvMain);
    }

    private void initData() {
        account = ((ApplicationContext) getApplication()).getAccount();
        if (account != null) {
            tvNavName.setText(account.getName_displayed());
            tvNavUsername.setText(account.getUsername());
        }

//        tvMain.setText("GG");
    }

    private class TaskOpenActivity extends AsyncTask<Intent, Void, Void> {

        @Override
        protected Void doInBackground(Intent... intents) {
            startActivity(intents[0]);
            return null;
        }
    }
}
