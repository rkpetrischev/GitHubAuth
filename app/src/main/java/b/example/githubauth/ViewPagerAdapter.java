package b.example.githubauth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private  String current_login;

    public ViewPagerAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
//    ViewPagerAdapter(FragmentActivity fragmentActivity, String current_login) {
//        super(fragmentActivity);
//        this.current_login= current_login;
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Current_login", current_login);
        switch (position){
            case 0 :
                UserInfoFragment userInfoFragment = new UserInfoFragment();
                userInfoFragment.setArguments(bundle);
                return userInfoFragment;
            case 1 :
                FollowersListFragment followersListFragment = new FollowersListFragment();
                followersListFragment.setArguments(bundle);
                return followersListFragment;
        }
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        userInfoFragment.setArguments(bundle);
        return userInfoFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}