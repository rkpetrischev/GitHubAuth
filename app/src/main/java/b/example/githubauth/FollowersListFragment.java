package b.example.githubauth;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import b.example.githubauth.databinding.ItemsFollowerBinding;
import b.example.githubauth.screens.MainActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollowersListFragment extends Fragment {
    private static final String CURRENT_LOGIN_TAG = "current_login";
    private final List<User> followers = new ArrayList<>();
    private String login;

    public FollowersListFragment() {
    }

    public static Fragment newInstance(String currentLogin) {
        final Fragment fragment = new FollowersListFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(CURRENT_LOGIN_TAG, currentLogin);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_followerslist, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = requireView().findViewById(R.id.followers);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        assert bundle != null;
        this.login = bundle.getString(CURRENT_LOGIN_TAG);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .client(MyHttpClient.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Disposable disposable = myAPICall.getFollowers(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                           followers.addAll(users);
                           ItemAdapter adapter = new ItemAdapter(followers,
                                   user->{
                                   this.login =user.getLogin();
                                   Log.i("TAG",login);
                                   MainActivity.start(getContext(),login);

                                   });
                           recycler.setAdapter(adapter);
                        }, Throwable::printStackTrace
                );

    }


    static class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyVH> {
        private final List<User> users = new ArrayList<>();
        private final ItemClickCallback callback;
        public ItemAdapter(List<User> users, ItemClickCallback callback) {
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


        private static class MyVH extends RecyclerView.ViewHolder {
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

}
