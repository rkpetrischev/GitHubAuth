package b.example.githubauth;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import b.example.githubauth.databinding.FrUserinfoBinding;

public class UserInfo extends Fragment {
    private FrUserinfoBinding binding;
    String login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        binding = FrUserinfoBinding.inflate(inflater);
        return inflater.inflate(R.layout.fr_userinfo, container, false);
//        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FrUserinfoBinding.bind(view);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/")
                .client(MyHttpClient.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Bundle bundle = getArguments();
        assert bundle != null;
        this.login = bundle.getString("Current_login");


        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Disposable disposable = myAPICall.getUser(login)
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
