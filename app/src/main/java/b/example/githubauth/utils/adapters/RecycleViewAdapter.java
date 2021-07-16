package b.example.githubauth.utils.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import b.example.githubauth.R;
import b.example.githubauth.databinding.ItemsFollowerBinding;
import b.example.githubauth.model.User;
import b.example.githubauth.screens.fragments.ItemClickCallback;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyVH> {
    private final List<User> users = new ArrayList<>();
    private final ItemClickCallback callback;

    public RecycleViewAdapter(List<User> users, ItemClickCallback callback) {
        this.users.addAll(users);
        this.callback = callback;
    }


    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int index) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_follower, parent, false);
        return new MyVH(view, callback);
    }


    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int index) {
        holder.bind(users.get(index));
    }


    @Override
    public int getItemCount() {
        return this.users.size();
    }


    public static class MyVH extends RecyclerView.ViewHolder {
        private final ItemClickCallback callback;
        final ItemsFollowerBinding binding;

        public MyVH(@NonNull View itemView, ItemClickCallback callback) {
            super(itemView);
            this.callback = callback;
            binding = ItemsFollowerBinding.bind(itemView);
        }

        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        public void bind(User user) {
            binding.username.setText(user.getLogin());
            binding.id.setText(String.format("Id = %d", user.getId()));
            binding.nodeId.setText("Node id = " + user.getNodeId());
            Picasso.get().load(user.getAvatarUrl()).into(binding.circleAvatar);
            binding.cardView.setOnClickListener(v -> callback.onItemClicked(user));
        }
    }
}

