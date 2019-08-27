package com.example.nick.rpggame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class SecondLevelActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* Setting game to full screen mode */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Delete title */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        /* Set main screen */
        this.setContentView(new SecondLevelSurface(this));
    }


}
