package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InformationActivity extends Activity {

    private TitleView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        bar = (TitleView) findViewById(R.id.title_view);
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
