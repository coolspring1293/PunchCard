package com.team10.punchcard;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.team10.punchcard.service.PunchcardService;
import com.team10.punchcard.service.ShanbayService;
import com.team10.punchcard.service.ToastFailureCallback;
import com.team10.punchcard.service.pojo.LoginRequest;
import com.team10.punchcard.unity.SpecialAdapter;
import com.team10.punchcard.unity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankingActivity extends Activity {

    private TitleView bar;
    private String[] names = new String[]{};
    private String[] rank = new String[]{};
    private int[] imgsrc = new int[]{R.drawable.mao1,R.drawable.mao2,R.drawable.mao3
    ,R.drawable.mao4,R.drawable.mao5};
    private  String[] gcAmount = new String[]{};
    private String [] day = new String[]{};
    PunchcardService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        bar = (TitleView) findViewById(R.id.title_view);

        bar.setTitleText(new String("排行榜"));
        bar.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("M_ACTION");
                startActivity(intent);
            }
        });

        CookieJar cookiejar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this));
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookiejar).build();

        service = new Retrofit.Builder().baseUrl(PunchcardService.END_POINT).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build().create(PunchcardService.class);
        service.login(new LoginRequest("leasunhy", "123456")).enqueue(new ToastFailureCallback<User>(bar) {
            @Override
            protected void onSuccess(Call<User> call, Response<User> response) {
                //  getUserInfo();
            }
        });

        final List<Map<String,Object>> listitems = new ArrayList<Map<String,Object>>();
      /*  for(int i=0;i<names.length;i++)
        {
            Map<String,Object> listitem = new HashMap<String,Object>();
            listitem.put("names",names[i]);
            listitem.put("rank",rank[i]);
            listitem.put("imgsrc",imgsrc[i]);
            listitems.add(listitem);
        }*/

        Call<List<User>> call = service.getAllUsers();
        call.enqueue(new ToastFailureCallback<List<User>>(bar, new NetworkErrorException()) {
            @Override
            public void onSuccess(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                String str = "";
                int i=0;
                for (User u : users)
                {
                    Map<String,Object> listitem = new HashMap<String,Object>();
                    listitem.put("names",u.getName());
                    listitem.put("rank",u.getRank());
                    listitem.put("imgsrc",imgsrc[i]);
                    i++;
                    listitem.put("gcAmount",u.goldCoinAmount);
                    listitem.put("day",u.getContinuousDays());
                    listitems.add(listitem);
                }


            }
        });
     /*   Snackbar.make(view, "Show: " + call.request().url(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();*/

        SpecialAdapter simpleAdapter = new SpecialAdapter(this,listitems,R.layout.simple_item,
                new String[] {"names","rank","imgsrc","gcAmount","day"}, new int[]{R.id.name,R.id.rank,R.id.img,R.id.gcAmount,R.id.continuousDays});
        ListView list = (ListView)findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);

    }

}
