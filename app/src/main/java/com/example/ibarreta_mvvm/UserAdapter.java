package com.example.ibarreta_mvvm;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList = new ArrayList<>();

    public void setUsers(List<User> users) {
        this.userList = users != null ? users : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (position >= userList.size()) return;

        User user = userList.get(position);

        holder.nameTv.setText(user.getName());
        holder.emailTv.setText(user.getEmail());
        holder.initialsTv.setText(getInitials(user.getName()));

        Drawable bg = holder.avatarView.getBackground();
        if (bg != null) {
            Drawable wrapped = DrawableCompat.wrap(bg);
            DrawableCompat.setTint(wrapped, getColorFromName(user.getName()));
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    private String getInitials(String name) {
        if (name == null || name.trim().isEmpty()) return "??";
        String[] parts = name.trim().split("\\s+");
        if (parts.length >= 2) {
            return (parts[0].substring(0, 1) +
                    parts[parts.length - 1].substring(0, 1)).toUpperCase();
        }
        return name.substring(0, 1).toUpperCase();
    }

    private int getColorFromName(String name) {
        if (name == null) name = "default";
        int[] colors = {
                0xFF6200EE,
                0xFF03DAC5,
                0xFF3700B3,
                0xFFCF6679,
                0xFF018786,
                0xFFF4511E
        };
        return colors[Math.abs(name.hashCode()) % colors.length];
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv, emailTv, initialsTv;
        View avatarView;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.textName);
            emailTv = itemView.findViewById(R.id.textEmail);
            initialsTv = itemView.findViewById(R.id.textInitials);
            avatarView = itemView.findViewById(R.id.avatarBackground);
        }
    }
}
