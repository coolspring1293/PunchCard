package com.team10.punchcard;

/**
 * Created by Eden on 4/23/16.
 */

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class AppStart extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.start, null);
        setContentView(view);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(1.0f,1.0f);
        aa.setDuration(4000);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}

        });


    }

    /**
     * 跳转到...
     */
    private void redirectTo(){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.setClassName(this, "com.team10.punchcard.MainActivity");
        startActivity(intent);
        finish();
    }
}
