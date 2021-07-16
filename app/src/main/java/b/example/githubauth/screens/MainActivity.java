package b.example.githubauth.screens;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import b.example.githubauth.screens.fragments.BlankFragment;
import b.example.githubauth.screens.fragments.flfragmentmvp.FollowersListFragment;
import b.example.githubauth.screens.fragments.UserInfoFragment;
import b.example.githubauth.databinding.ActivityMainBinding;
import b.example.githubauth.utils.adapters.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private final static String CURRENT_LOGIN_TAG = "current_login";
    private static final int USER_INFO_POSITION = 0;
    private static final int FOLLOWERS_POSITION = 1;

    private ActivityMainBinding binding;
    private String currentLogin;

    public static void start(Context context, String current_login) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(CURRENT_LOGIN_TAG, current_login);
        context.startActivity(starter);
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentLogin = getIntent().getStringExtra(CURRENT_LOGIN_TAG);


        final ViewPagerAdapter adapter = new ViewPagerAdapter(
                this,
                this::createFragmentByPosition,
                2
        );

        binding.pager.setAdapter(adapter);
    }

    private Fragment createFragmentByPosition(int position) {
        switch (position) {
            case USER_INFO_POSITION:
                return UserInfoFragment.newInstance(currentLogin != null ? currentLogin : "mojombo");

            case FOLLOWERS_POSITION:
                return FollowersListFragment.newInstance(currentLogin != null ? currentLogin : "mojombo");

            default:
                return BlankFragment.newInstance();
        }
    }
}