package com.burakkal.androidmvpexample.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burakkal.androidmvpexample.R;
import com.burakkal.androidmvpexample.model.local.entity.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_user_container, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);

        holder.imgIcon.setImageResource(R.drawable.ic_account_circle_black_24dp);
        holder.tvName.setText(user.getName());
        holder.tvSurname.setText(user.getSurname());
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView tvName;
        TextView tvSurname;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_user_icon);
            tvName = itemView.findViewById(R.id.tv_user_name);
            tvSurname = itemView.findViewById(R.id.tv_user_surname);
        }
    }
}
