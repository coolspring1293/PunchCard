package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MarketActivity extends Activity {

    private TitleView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);
        bar = (TitleView) findViewById(R.id.title_view);

        bar.setTitleText(new String("商城"));
        bar.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("M_ACTION");
                startActivity(intent);
            }
        });
    }
}
