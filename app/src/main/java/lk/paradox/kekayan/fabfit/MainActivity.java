package lk.paradox.kekayan.fabfit;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import lk.paradox.kekayan.fabfit.fragments.ProfileFragment;
import lk.paradox.kekayan.fabfit.fragments.SettingsFragment;
import lk.paradox.kekayan.fabfit.fragments.StepsFragment;
import lk.paradox.kekayan.fabfit.fragments.TweetsFragment;
import lk.paradox.kekayan.fabfit.sensors.SensorListener;

public class MainActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000;
    private TextView mTextMessage;
    private long mBackPressed;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private BottomNavigationView bottomNavigationView;
    //click events for bottom navigation buttons
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                    StepsFragment stepsFragment = new StepsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, stepsFragment)
                            .addToBackStack(null)
                            .commit();

                    return true;
                case R.id.navigation_tweets:
                    bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                    TweetsFragment tweetsFragment = new TweetsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, tweetsFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_settings:
                    bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                    SettingsFragment settingsFragment = new SettingsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, settingsFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
                case R.id.navigation_profile:
                    bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_container, profileFragment)
                            .addToBackStack(null)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.commit();
        } else if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Press again to exit the app", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        //start the sensorlistener service
        startService(new Intent(this, SensorListener.class));
        //loading the stepsfragment as main when app launched
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.frame_container);

        if (fragment == null) {
            fragment = new StepsFragment();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment).commit();
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
