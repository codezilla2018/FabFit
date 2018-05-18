package lk.paradox.kekayan.fabfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //specify  splash screen’s background as the activity’s theme background
        //So we can avoid blank white page appears during splash launching
        //we do not have setContentView() for this SplashActivity.
        // View is displaying from the theme and this way it is faster than creating a layout
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
