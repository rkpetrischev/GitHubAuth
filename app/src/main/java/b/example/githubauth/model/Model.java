package b.example.githubauth.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import b.example.githubauth.utils.adapters.RecycleViewAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Model {
    private List<User> users;


    public void setUsers(String login) {
        final Retrofit retrofit = RetrofitSingleton.getInstance();
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Disposable disposable = myAPICall.getFollowers(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                            this.users = users;


                        }, Throwable::printStackTrace
                );
    }

    public List<User> getUsers() {
        return users;
    }
}
