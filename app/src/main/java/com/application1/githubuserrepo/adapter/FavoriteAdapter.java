package com.application1.githubuserrepo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.application1.githubuserrepo.R;
import com.application1.githubuserrepo.models.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private ArrayList<User> modelUserArrayList = new ArrayList<>();

    public void setFavoriteUserList(ArrayList<User> data) {
        this.modelUserArrayList.clear();
        this.modelUserArrayList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new FavoriteAdapter.FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        User item = modelUserArrayList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        holder.Username.setText(item.getLogin());
        holder.Url.setText(item.getHtmlUrl());
        /*holder.cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL_USER, modelFollowersArrayList.get(position));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return modelUserArrayList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView Username, Url;
        ImageView imageUser;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            Username = itemView.findViewById(R.id.tvUsername);
            Url = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }

}