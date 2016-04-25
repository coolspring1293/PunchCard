package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WordsActivity extends Activity {

    private TitleView bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words);
        bar = (TitleView) findViewById(R.id.title_view);

        bar.setTitleText(new String("背单词"));
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
