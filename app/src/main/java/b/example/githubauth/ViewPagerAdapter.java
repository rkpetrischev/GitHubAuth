package b.example.githubauth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private  String current_login;
    ViewPagerAdapter(FragmentActivity fragmentActivity, String current_login) {
        super(fragmentActivity);
        this.current_login= current_login;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("Current_login", current_login);
        switch (position){
            case 0 :
                UserInfo userInfo = new UserInfo();
                userInfo.setArguments(bundle);
                return userInfo;
            case 1 :
                FollowersList followersList = new FollowersList();
                followersList.setArguments(bundle);
                return followersList;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setArguments(bundle);
        return userInfo;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}