package com.team10.punchcard;

import android.app.Application;
import android.util.Log;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.team10.punchcard.service.PunchcardService;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Eden on 6/3/16.
 */
public class Util extends Application {

    private String id = "test";
    private boolean isChecked = false;
    private PunchcardService service;
    private int num;
    private int total;


    @Override
    public void onCreate()
    {
        super.onCreate();//必须调用父类方法
        Log.i("CREATE","application created....");
        CookieJar cookiejar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookiejar).build();
        service = new Retrofit.Builder().baseUrl(PunchcardService.END_POINT).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build().create(PunchcardService.class);

        num = 0;
        total = 10;

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public boolean getisChecked()
    {
        return isChecked;
    }

    public void setisChecked(boolean isChecked)
    {
        this.isChecked = isChecked;
    }

    public PunchcardService getService()
    {
        return service;
    }

    public void setService(PunchcardService service)
    {
        this.service = service;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



}
