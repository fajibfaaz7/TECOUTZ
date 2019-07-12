package com.f7.outsiderz.tecoutz;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fajibfaaz on 25/03/17.
 */

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
                //here you can start your Activity B.
                finish();
            }

        }, 3000);

    }
}
