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
import com.team10.punchcard.service.pojo.LoginRequest;
import com.team10.punchcard.unity.User;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends Activity {

    private Button bn;
    private TextView tv;
    private TextView tv1;
    private PunchcardService service;
    private String psd;
    private String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

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
            name=new String(ed_name.getText().toString());
            psd=new String(ed_psd.getText().toString());
            // first login, then use the same http client to access other apis
            service.login(new LoginRequest(name, psd)).enqueue(new ToastFailureCallback<User>(bn) {
                @Override
                protected void onSuccess(Call<User> call, Response<User> response) {
                  /*  myApplication myapplication = (myApplication) getApplication();
                    myapplication.setUsername(name);
                    myapplication.setPsd(psd);*/
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
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
            intent.setClass(LoginActivity.this, SignupActivity.class);
            startActivity(intent);


        }

    }
}
