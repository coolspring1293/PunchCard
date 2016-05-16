package com.team10.punchcard.unity;

import android.renderscript.Long2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuw53 on 5/16/16.
 */
public class User {
    public String url = "http://14.liuw53.applinzi.com/json/index.php?";

    private int   id;
    private int    rank;
    private String name;
    private String password;
    private String avatar;
    private int    continuousDays;
    public  int    goldCoinAmount;
    public  int    goldCoinToday;

//    public function __construct($i, $n, $p, $a, $cd, $ga, $gt)
//    {
//        $this->id = $i;
//        $this->rank = -1;
//        $this->name = $n;
//        $this->password = $p;
//        $this->avatar = $a;
//        $this->continuousDays = $cd;
//        $this->goldCoinAmount = $ga;
//        $this->goldCoinToday = $gt;
//    }

    public User() {
        // id == -1 说明没有找到
        this.id = -1;
    }

    public User(int i, String n, String av, int a, int b, int c) {
        this.id = i;
        this.rank = -1;
        this.name = n;
        this.password = "No need to get the pwd";
        this.avatar = av;
        this.continuousDays = a;
        this.goldCoinAmount = b;
        this.goldCoinToday  = c;
    }

    public boolean register(String json) {
        boolean RegisterOK = false;
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.getInt("statue_code") == 200) {
                RegisterOK = true;
            }

//            JSONObject data = jsonObject.getJSONObject("user");
//
//            //maybe no use
//            this.id = data.getInt("id");
//            this.name = data.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return RegisterOK;
    }

    public User(String json) {
        try {
            // 如果没有json返回值，则这里会抛出异常并接住，
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("user");

            this.id = data.getInt("id");
            this.rank = data.getInt("rank");
            this.name = data.getString("name");
            this.password = data.getString("password");
            this.avatar = data.getString("avatar");
            this.continuousDays = data.getInt("continuousDays");
            this.goldCoinAmount = data.getInt("goldCoinAmount");
            this.goldCoinToday = data.getInt("goldCoinToday");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String tmp = "id: " + this.id + "\n";
        tmp += ("name: " + this.name + "\n");
        tmp += ("rand: " + (this.rank == -1 ? "No Rank!" : this.rank ) + "\n");
        tmp += ("avatar: " + this.avatar + "\n");
        tmp += ("continuousDays " + this.continuousDays + "\n");
        tmp += ("goldCoinAmount " + this.goldCoinAmount + "\n");
        tmp += ("goldCoinToday " + this.goldCoinToday + "\n");
        return tmp;
    }
}
