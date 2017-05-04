package com.example.andy.meizi.meizifragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andy.meizi.R;
import com.example.andy.meizi.meizifragment.model.Meizi;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * meizi adapter
 * Created by andy on 17-4-21.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.ViewHolder> {
    private List<Meizi> meizis = new ArrayList<>();
    private Context context;


    public void addMeizi(Context context, List<Meizi> meizis) {
        this.meizis = meizis;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meizi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(meizis.get(position).getUrl()).into(holder.ivMeizi);
        holder.tvTime.setText(meizis.get(position).getCreatedAt());
        holder.ivMeizi.setOnClickListener(v -> mListener.onItemClick(meizis.get(position), holder.ivMeizi));
    }

    @Override
    public int getItemCount() {
        return meizis == null ? 0 : meizis.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_meizi)
        ImageView ivMeizi;
        @Bind(R.id.tv_time)
        TextView tvTime;

        private ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(Meizi meizi, ImageView imageView);
    }

    private OnRecyclerViewItemClickListener mListener = null;

    public void setOnclickItemListener(OnRecyclerViewItemClickListener listener) {
        mListener = listener;
    }


}

