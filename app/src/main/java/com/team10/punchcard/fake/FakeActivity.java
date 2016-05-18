
package com.team10.punchcard.fake;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.team10.punchcard.service.ToastFailureCallback;
import com.team10.punchcard.service.PunchcardService;
import com.team10.punchcard.R;
import com.team10.punchcard.service.ShanbayService;
import com.team10.punchcard.service.pojo.LoginRequest;
import com.team10.punchcard.service.pojo.UserRegisterRequest;
import com.team10.punchcard.service.pojo.WordResponse;
import com.team10.punchcard.unity.User;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response; import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;

public class FakeActivity extends AppCompatActivity {
    private TextView tv;
    private EditText et;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    PunchcardService service;
    ShanbayService shanbayService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_fake);

        tv = (TextView)findViewById(R.id.tv_01);
        et = (EditText)findViewById(R.id.et_01);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // Cookies, for login
        CookieJar cookiejar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookiejar).build();

        service = new Retrofit.Builder().baseUrl(PunchcardService.END_POINT).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build().create(PunchcardService.class);
        shanbayService = new Retrofit.Builder().baseUrl(ShanbayService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create()).build().create(ShanbayService.class);

        // first login, then use the same http client to access other apis
        service.login(new LoginRequest("leasunhy", "123456")).enqueue(new ToastFailureCallback<User>(fab) {
            @Override
            protected void onSuccess(Call<User> call, Response<User> response) {
                getUserInfo();
            }
        });

//        queryWord();
//        getUserInfo();
//        Register();
//        getAllUser();
    }


    /**
     * 获取好友列表。排行榜等
     */
    private void getAllUser() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<User>> call = service.getAllUsers();
                call.enqueue(new ToastFailureCallback<List<User>>(view, new NetworkErrorException()) {
                    @Override
                    public void onSuccess(Call<List<User>> call, Response<List<User>> response) {
                        List<User> users = response.body();
                        String str = "";
                        for (User u : users)
                            str += u.toString() + "\n";
                        tv.setText(str);
                    }
                });
                Snackbar.make(view, "Show: " + call.request().url(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    /** 注册
     *
     * @url 接口URL(包含username和password)
     *  返回值是否注册成功
     *  测试样例username=liuw53@password=1234能注册成功，但是这里是php的弱类型。能不能绕过看人品
     *
     */
    private void Register() {
        final UserRegisterRequest userinfo = new UserRegisterRequest();
        userinfo.password = "123456";
        userinfo.userName = "coolspring";
        userinfo.name = "coolspring";
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = service.registerUser(userinfo);
                call.enqueue(new ToastFailureCallback<User>(view, new IllegalArgumentException("Register failed.")) {
                    @Override
                    protected void onSuccess(Call<User> call, Response<User> response) {
                        User user = response.body();
                        tv.setText(user.toString());
                    }
                });
                Snackbar.make(view, call.request().url().toString(), Snackbar.LENGTH_LONG).show();
            }
        });
    }

    // 实现方法getUserInfo()一样，找不则返回为user.username => -1
    private void Login() {}

    /** 获取一个用户的信息
    * */
    private void getUserInfo() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = service.getUserInfo();
                call.enqueue(new ToastFailureCallback<User>(view, new IllegalArgumentException("Invalid username.")) {
                    @Override
                    public void onSuccess(Call<User> call, Response<User> response) {
                        User user = response.body();
                        tv.setText(user.toString());
                    }
                });
                Snackbar.make(view, "Show: " + call.request().url(), Snackbar.LENGTH_LONG)
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_input = et.getText().toString().trim();
                tv.setText("");
                Call<WordResponse> call = shanbayService.getWord(user_input);
                call.enqueue(new ToastFailureCallback<WordResponse>(view, new IllegalArgumentException("Word not found.")) {
                    @Override
                    public void onSuccess(Call<WordResponse> call, Response<WordResponse> response) {
                        tv.setText(response.body().data.toString());
                    }
                });
                Snackbar.make(view, "Show: " + call.request().url(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
