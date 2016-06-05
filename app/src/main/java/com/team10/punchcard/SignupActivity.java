package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.team10.punchcard.service.PunchcardService;
import com.team10.punchcard.service.ToastFailureCallback;
import com.team10.punchcard.service.pojo.UserRegisterRequest;
import com.team10.punchcard.unity.User;

import retrofit2.Call;
import retrofit2.Response;

public class SignupActivity extends Activity {

    private Button bn;
    private TextView tv;
    private TextView tv1;
    private PunchcardService service;
    private String psd;
    private String name;
    private String nick;
    private String psda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        bn = (Button) findViewById(R.id.bt);
        bn.setOnClickListener(new bnClickListener());


        tv = (TextView)findViewById(R.id.tv2);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setOnClickListener(new tvClickListener());

        Util util = (Util) this.getApplication();
        service = util.getService();

    }

    class bnClickListener implements View.OnClickListener
    {
        public void onClick(View v)
        {

            EditText ed_name = (EditText) findViewById(R.id.ed_name);
            EditText ed_psd = (EditText) findViewById(R.id.ed_psd);
            EditText ed_name1 = (EditText) findViewById(R.id.ed_name1);
            EditText ed_psd1 = (EditText) findViewById(R.id.ed_psd1);
            name=new String(ed_name.getText().toString());
            psd=new String(ed_psd.getText().toString());
            nick=new String(ed_name1.getText().toString());
            psda=new String(ed_psd1.getText().toString());

            final UserRegisterRequest userinfo = new UserRegisterRequest();
            userinfo.password = psd;
            userinfo.userName = name;
            userinfo.name = nick;

            Call<User> call = service.registerUser(userinfo);
            call.enqueue(new ToastFailureCallback<User>(v, new IllegalArgumentException("Registration failed.")) {
                @Override
                protected void onSuccess(Call<User> call, Response<User> response) {
                    Intent intent = new Intent();
                    intent.setClass(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    class tvClickListener implements View.OnClickListener
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(SignupActivity.this, LoginActivity.class);
            startActivity(intent);


        }

    }
}
