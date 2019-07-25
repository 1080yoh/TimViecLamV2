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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.server.ServerInteractor;
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
        loadData();
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
        TaskOpenActivity taskOpenActivity = new TaskOpenActivity();
        taskOpenActivity.execute(intent);
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
    }

    @Override
    public void loadData() {
        account = ((ApplicationContext) getApplication()).getAccount();
        if (account != null && account.getId() != -1) {
            tvNavName.setText(account.getName_displayed());
            tvNavUsername.setText(account.getUsername());
            setNavMenu(R.menu.menu_nav_main);
            Glide.with(this).load(account.getAvatar()).apply(RequestOptions.circleCropTransform()).into(ivNavAvatar);
        } else {
            Random rd = new Random();
            String name = "User no." + Math.abs(rd.nextInt() % 10000);
            tvNavName.setText(name);
            tvNavUsername.setText(name);
            setNavMenu(R.menu.menu_nav_main_not_login);
            Glide.with(this).load(ServerInteractor.DEFAULT_USER_IMG).apply(RequestOptions.circleCropTransform()).into(ivNavAvatar);
        }
    }

    @Override
    public void setNavMenu(int idMenu) {
        navigationView.getMenu().clear();
        navigationView.inflateMenu(idMenu);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class TaskOpenActivity extends AsyncTask<Intent, Void, Void> {
        @Override
        protected Void doInBackground(Intent... intents) {
            startActivity(intents[0]);
            return null;
        }
    }
}
