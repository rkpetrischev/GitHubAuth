package b.example.githubauth.screens.fragments.flfragmentmvp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import b.example.githubauth.R;
import b.example.githubauth.screens.fragments.ItemClickCallback;

public class FollowerRecycleAdapter extends RecyclerView.Adapter<FollowerViewHolder> {

    private final UserPresenter presenter;
    private ItemClickCallback callback;

    public FollowerRecycleAdapter(UserPresenter repositoriesPresenter) {
        this.presenter = repositoriesPresenter;
    }

    @Override
    public FollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_follower, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerViewHolder holder, int position) {
        presenter.onBindRepositoryRowViewAtPosition(position, holder);

    }

    @Override
    public int getItemCount() {
        return presenter.getRepositoriesRowsCount();
    }
}