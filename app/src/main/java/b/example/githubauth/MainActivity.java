package b.example.githubauth;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;



public class MainActivity extends AppCompatActivity {
     private  String current_login;

     public static void start(Context context, String current_login) {
         Intent starter = new Intent(context, MainActivity.class);
         starter.putExtra("Current_login", current_login );
         context.startActivity(starter);
     }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            this.current_login = bundle.getString("Current_login");
            Log.d("TAG",current_login);
        }

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewPager2 pager = findViewById(R.id.pager);
        if (current_login!=null){
        pager.setAdapter(new ViewPagerAdapter(this, current_login));}
        else {
            pager.setAdapter(new ViewPagerAdapter(this, "mojombo"));
        }

    }
}