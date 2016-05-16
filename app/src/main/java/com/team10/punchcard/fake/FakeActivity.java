package com.team10.punchcard.fake;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.team10.punchcard.R;
import com.team10.punchcard.unity.Basic;
import com.team10.punchcard.unity.HttpUrlConnection;

public class FakeActivity extends AppCompatActivity {


    String url = "https://api.shanbay.com/bdc/search/?word=connect";


    private TextView tv;
    private EditText et;
    private boolean isSelect = false;
    private String showout = "";

    public Basic basic = new Basic("Wang Liu");

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case HttpUrlConnection.PARSESUCCWSS:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tv = (TextView) findViewById(R.id.tv_01);
        et = (EditText) findViewById(R.id.et_01);


        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler, url);
        httpUrlConnection.getJsonFromInternet();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = et.getText().toString();
                url = "https://api.shanbay.com/bdc/search/?word=" + user_input;

                try {
                    basic.decodeJsonToWord(httpUrlConnection.getResult());
                    //showout = httpUrlConnection.getResult();
                    showout = basic.getWord().toString();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                tv.setText(showout);
                isSelect = !isSelect;
                Snackbar.make(view, "Show: " + url, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
