package com.kevin.nodelogin.Login.login.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kevin.nodelogin.Login.login.home.presenter.HomeContract;
import com.kevin.nodelogin.Login.login.home.model.User;
import com.kevin.nodelogin.R;
import java.util.List;
import butterknife.ButterKnife;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<User> records;
    private HomeContract.Listener listListener;

    public UsersAdapter( List<User> records, HomeContract.Listener listListener) {
        this.records = records;
        this.listListener = listListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        View view;
        ImageView delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);
        ButterKnife.bind(this, itemView);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setIsRecyclable(false);
        User user = records.get(position);
        holder.name.setText(user.getUsername());
        holder.delete.setOnClickListener(v->listListener.onDelete(user.getId()));

    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}
