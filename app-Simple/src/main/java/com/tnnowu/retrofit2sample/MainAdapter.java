package com.tnnowu.retrofit2sample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tnno Wu on 2017/09/13.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private static final String TAG = MainAdapter.class.getSimpleName();

    private List<Contributor> mList = new ArrayList<>();

    private MainCallback mCallback;

    public void setCallback(MainCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainAdapter.MainViewHolder holder, int position) {
        Contributor contributor = mList.get(position);

        final String login = contributor.getLogin();
        String htmlUrl = contributor.getHtml_url();
        String avatarUrl = contributor.getAvatar_url();

        Log.d(TAG, "onBindViewHolder: " + login);
        Log.d(TAG, "onBindViewHolder: " + htmlUrl);
        Log.d(TAG, "onBindViewHolder: " + avatarUrl);

        holder.contributor = contributor;
        holder.tvName.setText(login);
        holder.tvUrl.setText(htmlUrl);
        Glide.with(holder.ivAvatar.getContext()).load(avatarUrl).into(holder.ivAvatar);

        holder.contentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCallback != null) {
                    mCallback.onItemClick(holder.contributor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        View contentLayout;
        TextView tvName, tvUrl;
        ImageView ivAvatar;
        Contributor contributor;

        public MainViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUrl = itemView.findViewById(R.id.tv_url);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
        }
    }

    public void setData(List<Contributor> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public interface MainCallback {
        void onItemClick(Contributor contributor);
    }
}
