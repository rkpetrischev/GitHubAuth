package b.example.githubauth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import b.example.githubauth.databinding.FrUserinfoBinding;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserInfoFragment extends Fragment {

    private static final String CURRENT_LOGIN_TAG = "current_login";

    private FrUserinfoBinding binding;
    private String login;
    private Disposable disposable;

    public UserInfoFragment() {
    }

    public static Fragment newInstance(String currentLogin) {
        final Fragment fragment = new UserInfoFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(CURRENT_LOGIN_TAG, currentLogin);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        binding = FrUserinfoBinding.inflate(inflater);
//        return binding.getRoot();
        return inflater.inflate(R.layout.fr_userinfo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FrUserinfoBinding.bind(view);
        final Bundle bundle = getArguments();

        if (bundle != null && bundle.getString(CURRENT_LOGIN_TAG) != null) {
            final Retrofit retrofit = RetrofitSingleton.getInstance();

            MyAPICall myAPICall = retrofit.create(MyAPICall.class);

            disposable = myAPICall.getUser(bundle.getString(CURRENT_LOGIN_TAG))
                    .subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                                binding.name.setText(user.getLogin());
                                Picasso.get().load(user.getAvatarUrl()).into(binding.avatar);
                                Picasso.get().load(user.getAvatarUrl()).into(binding.circleAvatar);
                            }, Throwable::printStackTrace
                    );
        }
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}
