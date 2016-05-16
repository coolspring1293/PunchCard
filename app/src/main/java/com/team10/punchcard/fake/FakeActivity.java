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
import com.team10.punchcard.unity.User;
import com.team10.punchcard.unity.Word;

public class FakeActivity extends AppCompatActivity {


    String url = "https://api.shanbay.com/bdc/search/?word=connect";


    private TextView tv;
    private EditText et;
    private boolean isSelect = false;
    private String showout = "";
    private FloatingActionButton fab;



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
        fab = (FloatingActionButton) findViewById(R.id.fab);

        // queryWord();


        register();


    }


    /** 注册
     * @content
     * @url 接口URL(包含username和password)
     * @user.register() 返回值是否注册成功
     */
    private void register() {
        et.setText("username=liuw53&password=1234");
        final String content = "username=liuw53&password=1234";
        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler,
                "http://www.liuw53.top/json/register/index.php?" + content);
        httpUrlConnection.getJsonFromInternet();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = et.getText().toString();
                String urlReg = "http://www.liuw53.top/json/register/index.php?" + content;

                try {
                    User user = new User();
                    //boolean isOK = user.register(httpUrlConnection.getResult());
                    tv.setText(httpUrlConnection.getResult());

//                    if (isOK) tv.setText("Register Successfuly!");
//                    else      tv.setText("Register Failed!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                isSelect = !isSelect;
                if (isSelect) { tv.setText(""); }
                else tv.setText(showout);

                Snackbar.make(view, "Show: " + urlReg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /** 获取一个用户的信息
    * */
    private void getUserInfo() {
        et.setText("name=liuw53&password=nopwd");

        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler,
                "http://www.liuw53.top/json/index.php?" + "name=liuw53&password=nopwd");
        httpUrlConnection.getJsonFromInternet();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = et.getText().toString();
                String urlReg = "http://www.liuw53.top/json/index.php?" + user_input;

                try {
                    User user = new User(httpUrlConnection.getResult());
                    showout = user.toString();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                isSelect = !isSelect;
                if (isSelect) { tv.setText(""); }
                else tv.setText(showout);

                Snackbar.make(view, "Show: " + urlReg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /** 查询单词详细信息
     * @user_input 输入的单词
     * @url 接口URL
     * @word
     */
    private void queryWord() {
        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler, url);
        httpUrlConnection.getJsonFromInternet();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = et.getText().toString();
                url = "https://api.shanbay.com/bdc/search/?word=" + user_input;

                try {
                    // get details of this word
                    Word word = new Word(httpUrlConnection.getResult());
                    showout = word.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                isSelect = !isSelect;
                if (isSelect) { tv.setText(""); }
                else tv.setText(showout);

                Snackbar.make(view, "Show: " + url, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
