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


        // getUserInfo();
        Register();

    }


    /** 注册
     *
     * @url 接口URL(包含username和password)
     *  返回值是否注册成功
     *  测试样例username=liuw53@password=1234能注册成功，但是这里是php的弱类型。能不能绕过看人品
     *
     */
    private void Register() {

        final String content = "username=liggg3&password=1234";
        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler,
                "http://www.liuw53.top/json/register/index.php?" + content);
        httpUrlConnection.getJsonFromInternet();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlReg = "http://www.liuw53.top/json/register/index.php?" + content;

                try {
                    User user = new User();
                    boolean isOK = user.register(httpUrlConnection.getResult());
                    if (isOK) showout = "Register Successfuly!";
                    else      showout = "Register Failed!";
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

    // 实现方法getUserInfo()一样，找不则返回为null
    private void Login() {}

    /** 获取一个用户的信息
    * */
    private void getUserInfo() {


        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler,
                "http://www.liuw53.top/json/index.php?" + "name=liuw53&password=nopwd");
        httpUrlConnection.getJsonFromInternet();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlReg = "http://www.liuw53.top/json/index.php?" + "username=liuw53";

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
