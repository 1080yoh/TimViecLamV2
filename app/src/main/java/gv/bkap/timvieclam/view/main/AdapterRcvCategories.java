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
import gv.bkap.timvieclam.model.entity.Category;

public class AdapterRcvCategories extends RecyclerView.Adapter<AdapterRcvCategories.CategoryItemHolder> {

    Context context;
    ArrayList<Category> lstCategories;

    public AdapterRcvCategories(Context context, ArrayList<Category> lstCategories) {
        this.context = context;
        this.lstCategories = lstCategories;
    }

    @NonNull
    @Override
    public CategoryItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, null, false);
        return new CategoryItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemHolder viewHolder, int position) {
        Category category = lstCategories.get(position);
        viewHolder.tvCategoryName.setText(category.getName());
        Glide.with(context).load(category.getIcon_link()).into(viewHolder.ibIconCategory);
    }

    @Override
    public int getItemCount() {
        return lstCategories.size();
    }

    class CategoryItemHolder extends RecyclerView.ViewHolder {
        ImageView ibIconCategory;
        TextView tvCategoryName;

        public CategoryItemHolder(@NonNull View itemView) {
            super(itemView);
            this.ibIconCategory = itemView.findViewById(R.id.ibIconItemCategory);
            this.tvCategoryName = itemView.findViewById(R.id.tvItemCategoryName);
        }
    }
}
