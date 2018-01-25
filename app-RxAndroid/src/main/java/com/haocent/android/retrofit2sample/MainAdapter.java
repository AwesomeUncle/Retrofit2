package com.haocent.android.retrofit2sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tnno Wu on 2018/01/25.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private static final String TAG = MainAdapter.class.getSimpleName();

    private Context mContext;

    private List<GitHubBean> mList = new ArrayList<>();

    public MainAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<GitHubBean> list) {
        mList = list;

        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        // Avatar
        GlideApp.with(mContext).load(mList.get(position).getAvatar_url()).into(holder.ivAvatar);
        // Name
        holder.tvName.setText(mList.get(position).getLogin());
        // Url
        holder.tvUrl.setText(mList.get(position).getHtml_url());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvName, tvUrl;

        public MainViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUrl = itemView.findViewById(R.id.tv_url);
        }
    }
}
