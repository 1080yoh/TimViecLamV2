package gv.bkap.timvieclam.view.postedjobs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gv.bkap.timvieclam.R;
import gv.bkap.timvieclam.model.entity.PostedJobItem;

public class PostedJobAdapter extends BaseAdapter {

    private static final int HEADER_TIME = 0;
    private static final int ITEM_JOB = 1;
    private ArrayList<Object> lstData;
    private Context context;


    public PostedJobAdapter(Context context, ArrayList<Object> lstData) {
        this.context = context;
        this.lstData = lstData;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = lstData.get(position);
        return obj instanceof String ? HEADER_TIME : ITEM_JOB;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return lstData.size();
    }

    @Override
    public Object getItem(int position) {
        return lstData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            switch (getItemViewType(position)) {
                case HEADER_TIME:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_posted_job_header, parent, false);
                    viewHolder = new ViewHolderHeader();
                    ((ViewHolderHeader) viewHolder).tvHeaderTime = convertView.findViewById(R.id.tvPostedJobHeader);
                    convertView.setTag(viewHolder);
                    break;
                case ITEM_JOB:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_posted_job, parent, false);
                    viewHolder = new ViewHolderPostedJob();
                    ((ViewHolderPostedJob) viewHolder).tvJobName = convertView.findViewById(R.id.tvJobName);
                    ((ViewHolderPostedJob) viewHolder).tvSalary = convertView.findViewById(R.id.tvSalary);
                    ((ViewHolderPostedJob) viewHolder).tvQuantity = convertView.findViewById(R.id.tvQuantity);
                    ((ViewHolderPostedJob) viewHolder).tvDescription = convertView.findViewById(R.id.tvDescription);
                    ((ViewHolderPostedJob) viewHolder).tvLocation = convertView.findViewById(R.id.tvLocation);
                    ((ViewHolderPostedJob) viewHolder).tvContact = convertView.findViewById(R.id.tvContact);
                    convertView.setTag(viewHolder);
                    break;
                default:
                    break;
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        switch (getItemViewType(position)) {
            case HEADER_TIME:
                String data = (String) lstData.get(position);
                ((ViewHolderHeader) viewHolder).tvHeaderTime.setText(data);
                break;
            case ITEM_JOB:
                PostedJobItem postedJobItem = (PostedJobItem) lstData.get(position);
                ((ViewHolderPostedJob) viewHolder).tvJobName.setText(postedJobItem.getJobName());
                ((ViewHolderPostedJob) viewHolder).tvSalary.setText(postedJobItem.getSalary());
                ((ViewHolderPostedJob) viewHolder).tvQuantity.setText(postedJobItem.getQuantity() + "");
                ((ViewHolderPostedJob) viewHolder).tvDescription.setText(postedJobItem.getDescription());
                ((ViewHolderPostedJob) viewHolder).tvLocation.setText(postedJobItem.getLocation());
                ((ViewHolderPostedJob) viewHolder).tvContact.setText(postedJobItem.getContact());
        }
        return convertView;
    }

    private interface ViewHolder {
    }

    private static class ViewHolderPostedJob implements ViewHolder {
        TextView tvJobName;
        TextView tvSalary;
        TextView tvQuantity;
        TextView tvLocation;
        TextView tvDescription;
        TextView tvContact;
    }

    private static class ViewHolderHeader implements ViewHolder {
        TextView tvHeaderTime;
    }
}
