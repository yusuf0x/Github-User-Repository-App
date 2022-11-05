package com.application1.githubuserrepo.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.models.Follower;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.MyViewHolder>{
    private ArrayList<Follower> FollowArrayList = new ArrayList<>();
    private Context context;

    public FollowAdapter(Context context) {
        this.context = context;
    }

    public void setFollowList(ArrayList<Follower> items) {
        FollowArrayList.clear();
        FollowArrayList.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Follower item = FollowArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.Username.setText(item.getLogin());
        holder.Url.setText(item.getHtmlUrl());
    }

    @Override
    public int getItemCount() {
        return FollowArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView ListUser;
        TextView Username, Url;
        ImageView imageUser, imageArrow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ListUser = itemView.findViewById(R.id.cvListUser);
            Username = itemView.findViewById(R.id.tvUsername);
            Url = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
            imageArrow = itemView.findViewById(R.id.save);

            imageArrow.setVisibility(View.GONE);
        }
    }
}
