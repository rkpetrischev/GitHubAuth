package b.example.githubauth.utils.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final FragmentInitializer fragmentInitializer;
    private final int fragmentsCount;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                            @NonNull FragmentInitializer fragmentInitializer,
                            int fragmentsCount) {
        super(fragmentActivity);
        this.fragmentInitializer = fragmentInitializer;
        this.fragmentsCount = fragmentsCount;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentInitializer.createFragmentByPosition(position);
    }

    @Override
    public int getItemCount() {
        return fragmentsCount;
    }

    public interface FragmentInitializer {
        Fragment createFragmentByPosition(int position);
    }
}
