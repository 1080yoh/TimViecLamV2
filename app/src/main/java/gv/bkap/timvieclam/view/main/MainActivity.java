package gv.bkap.timvieclam.view.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Random;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.ApplicationContext;
import gv.bkap.timvieclam.model.entity.Account;
import gv.bkap.timvieclam.model.entity.Category;
import gv.bkap.timvieclam.model.entity.Job;
import gv.bkap.timvieclam.model.entity.JobItem;
import gv.bkap.timvieclam.model.server.ServerInteractor;
import gv.bkap.timvieclam.presenter.main.IMainPresenter;
import gv.bkap.timvieclam.presenter.main.MainPresenter;
import gv.bkap.timvieclam.view.AbsActivityHasNavDrawable;
import gv.bkap.timvieclam.view.detailcustomer.DetailCustomerActivity;
import gv.bkap.timvieclam.view.dialog.ProgressDialog;
import gv.bkap.timvieclam.view.login.LoginActivity;
import gv.bkap.timvieclam.view.postedjobs.PostedJobsActivity;
import gv.bkap.timvieclam.view.registerjob.RegisterJobActivity;

public class MainActivity extends AbsActivityHasNavDrawable implements NavigationView.OnNavigationItemSelectedListener, IMainView {

    Account account;
    private IMainPresenter mainPresenter;
    private TextView tvNavName;
    private TextView tvNavUsername;
    public static final String NAMEJOB = "NAMEJOB";
    public static final String SALARY = "SALARY";
    public static final String LOCATION = "LOCATION";
    private ImageView ivNavAvatar;
    public static final String BUNDLE = "BUNDLE";
    private TextView tvNameJob;
    private TextView tvSalary;
    private TextView tvLocation;
    private RecyclerView rcvCategories;
    private AdapterRcvCategories adapterRcvCategories;
    private ArrayList<Category> lstCategories;
    private ArrayList<Job> lstJob;
    private RecyclerView rcvJobItems;
    private AdapterRcvJobs adapterRcvJobs;
    private ArrayList<JobItem> lstJobItems;

    private ProgressDialog progressDialog;

    // 1: visible, 0: invisible
    boolean isMenuVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNavDrawable();
        mainPresenter = new MainPresenter(this, getApplicationContext());
        initComps();
        initData();
        loadData();
        addEvents();
        mainPresenter.loadCategories();
        mainPresenter.loadJobItems();
    }

    private void initData() {
        lstCategories = new ArrayList<>();
        adapterRcvCategories = new AdapterRcvCategories(this, lstCategories);
        // set layout horizontal
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvCategories.setLayoutManager(layoutManager);
        // set khoảng cách
        ItemOffsetDecoration dividerItemDecoration = new ItemOffsetDecoration(this,
                R.dimen.divider_category);
        rcvCategories.addItemDecoration(dividerItemDecoration);
        rcvCategories.setAdapter(adapterRcvCategories);

        lstJobItems = new ArrayList<>();
        adapterRcvJobs = new AdapterRcvJobs(this, lstJobItems);
        // set layout
        LinearLayoutManager layoutManagerJobItem
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvJobItems.setLayoutManager(layoutManagerJobItem);
        // thêm đường ngăn
        DividerItemDecoration dividerJobItems = new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL);
        rcvJobItems.addItemDecoration(dividerJobItems);
        // set adapter
        rcvJobItems.setAdapter(adapterRcvJobs);

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
        if (isMenuVisible) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(false);
                isMenuVisible = false;
            }
        }
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
    public void navigateToDetailCustomerActivity() {
        Intent intent = new Intent(this, DetailCustomerActivity.class);
        TaskOpenActivity taskOpenActivity = new TaskOpenActivity();
        taskOpenActivity.execute(intent);
    }

    @Override
    public void navigateToRegisterJobActivity() {
        Intent intent = new Intent(this, RegisterJobActivity.class);
        TaskOpenActivity taskOpenActivity = new TaskOpenActivity();
        taskOpenActivity.execute(intent);
    }

    @Override
    public void navigateToPostedJobsActivity() {
        Intent intent = new Intent(this, PostedJobsActivity.class);
        startActivity(intent);
    }

    @Override
    public void loadCategories(ArrayList<Category> lstCategories) {
        this.lstCategories.clear();
        this.lstCategories.addAll(lstCategories);
        this.adapterRcvCategories.notifyDataSetChanged();
    }

    @Override
    public void loadJobItems(ArrayList<JobItem> lstJobItems) {
        this.lstJobItems.clear();
        this.lstJobItems.addAll(lstJobItems);
        this.adapterRcvJobs.notifyDataSetChanged();
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
        rcvCategories = findViewById(R.id.rcvCategories);

        rcvJobItems = findViewById(R.id.rcvJobItems);

        progressDialog = new ProgressDialog(this);

        tvNameJob = findViewById(R.id.tvNameJob);
        tvSalary = findViewById(R.id.tvSalary);
        tvLocation = findViewById(R.id.tvLocation);
    }


    private void addEvents() {
        rcvCategories.addOnItemTouchListener(new RcvItemOnClickListener(this, rcvCategories, new RcvItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mainPresenter.filterOutJobs(lstCategories.get(position).getId());
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // do anything
            }
        }));

        rcvJobItems.addOnItemTouchListener(new RcvItemOnClickListener(this, rcvJobItems, new RcvItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mainPresenter.detailJobItem(lstJobItems.get(position).getId());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
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

    private class TaskOpenActivity extends AsyncTask<Intent, Void, Void> {
        @Override
        protected Void doInBackground(Intent... intents) {
            startActivity(intents[0]);
            return null;
        }
    }
    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }

    }
}
