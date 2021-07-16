package b.example.githubauth.screens.fragments.flfragmentmvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


import b.example.githubauth.model.MyAPICall;
import b.example.githubauth.R;
import b.example.githubauth.model.RetrofitSingleton;
import b.example.githubauth.model.User;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


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
    public synchronized void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = requireView().findViewById(R.id.followers);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        assert bundle != null;
        this.login = bundle.getString(CURRENT_LOGIN_TAG);
        UserPresenter presenter = new UserPresenter(null);
        presenter.setUsers(login);
        final Retrofit retrofit = RetrofitSingleton.getInstance();
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Disposable disposable = myAPICall.getFollowers(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                            followers.addAll(users);
                            FollowerRecycleAdapter adapter = new FollowerRecycleAdapter(new UserPresenter(followers));/*new UserPresenter(Collections.singletonList(new User("jopa", 1, "jopa")))*/
                            recycler.setAdapter(adapter);
                        }, Throwable::printStackTrace
                );
//        FollowerRecycleAdapter adapter = new FollowerRecycleAdapter(presenter);/*new UserPresenter(Collections.singletonList(new User("jopa", 1, "jopa")))*/
//        recycler.setAdapter(adapter);

    }


}
