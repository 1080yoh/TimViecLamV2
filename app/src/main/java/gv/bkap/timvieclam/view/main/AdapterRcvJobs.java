package gv.bkap.timvieclam.view.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.entity.JobItem;

public class AdapterRcvJobs extends RecyclerView.Adapter<AdapterRcvJobs.JobItemHolder> {

    Context context;
    ArrayList<JobItem> lstJobItems;

    public AdapterRcvJobs(Context context, ArrayList<JobItem> lstJobItems) {
        this.context = context;
        this.lstJobItems = lstJobItems;
    }


    @NonNull
    @Override
    public JobItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_job, null);
        JobItemHolder jobItemHolder = new JobItemHolder(v);
        return jobItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobItemHolder jobItemHolder, int i) {
        JobItem jobItem = lstJobItems.get(i);
        Glide.with(context).load(jobItem.getLogoLink()).into(jobItemHolder.ivLogo);
        jobItemHolder.tvJobName.setText(jobItem.getJobName());
        jobItemHolder.tvRenterName.setText(jobItem.getRenterName());
        jobItemHolder.tvLocation.setText(jobItem.getLocation());
        jobItemHolder.tvSalary.setText(jobItem.getSalary());
        jobItemHolder.tvTimeToNow.setText(jobItem.getTimeUptoNow());
    }

    @Override
    public int getItemCount() {
        return lstJobItems.size();
    }

    class JobItemHolder extends RecyclerView.ViewHolder {

        ImageView ivLogo;
        TextView tvJobName;
        TextView tvRenterName;
        TextView tvLocation;
        TextView tvSalary;
        TextView tvTimeToNow;

        public JobItemHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvJobName = itemView.findViewById(R.id.tvNameJob);
            tvRenterName = itemView.findViewById(R.id.tvNameRenter);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            tvTimeToNow = itemView.findViewById(R.id.tvTimeUpToNow);
        }
    }
}
