package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.team10.punchcard.service.PunchcardService;
import com.team10.punchcard.service.ToastFailureCallback;
import com.team10.punchcard.unity.User;

import retrofit2.Call;
import retrofit2.Response;

public class InformationActivity extends Activity {

    private TitleView bar;
    private TextView name;
    private TextView coin;
    private TextView day;
    private TextView rank;
    private PunchcardService service;
    private Util util;



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

        rank = (TextView) findViewById(R.id.editText3);
        name = (TextView) findViewById(R.id.editText);
        coin = (TextView) findViewById(R.id.editText1);
        day = (TextView) findViewById(R.id.editText2);

        util = (Util) this.getApplication();
        service = util.getService();

        Call<User> call = service.getUserInfo();
        call.enqueue(new ToastFailureCallback<User>(name, new IllegalArgumentException("Invalid username.")) {
            @Override
            public void onSuccess(Call<User> call, Response<User> response) {
                User user = response.body();
                name.setText(user.getName());
                coin.setText('$' + String.valueOf(user.getGoldCoinAmount()));
                if (util.getisChecked())
                    day.setText(String.valueOf(user.getContinuousDays()+1) + "天");
                else
                    day.setText(String.valueOf(user.getContinuousDays()+1) + "天");
                rank.setText("No." + String.valueOf(user.getRank()));
            }
        });
    }
}
