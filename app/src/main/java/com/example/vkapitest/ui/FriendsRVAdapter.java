package com.example.vkapitest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkapitest.R;
import com.example.vkapitest.entity.VkFriend;

import org.jetbrains.annotations.NotNull;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

class FriendsRVAdapter extends RealmRecyclerViewAdapter<VkFriend, FriendsRVAdapter.MyViewHolder> {

    FriendsRVAdapter(OrderedRealmCollection<VkFriend> data) {
        super(data, true);
        setHasStableIds(true);
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_friend, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        final VkFriend vkFriend = getItem(position);
        holder.data = vkFriend;

        if (vkFriend != null) {
            holder.tvId.setText(String.format("%d", vkFriend.getId()));
            holder.tvName.setText(vkFriend.getName());
        }

    }

    @Override
    public long getItemId(int index) {
        return getItem(index).getId();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvName;

        VkFriend data;

        MyViewHolder(View view) {
            super(view);
            tvId = view.findViewById(R.id.tv_id);
            tvName = view.findViewById(R.id.tv_name);
        }
    }
}
