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
//public class User {
//    public String url = "http://14.liuw53.applinzi.com/json/index.php?";
//
//    private int   id;
//    private int    rank;
//    private String name;
//    private String password;
//    private String avatar;
//    private int    continuousDays;
//    public  int    goldCoinAmount;
//    public  int    goldCoinToday;
//
////    public function __construct($i, $n, $p, $a, $cd, $ga, $gt)
////    {
////        $this->username = $i;
////        $this->rank = -1;
////        $this->name = $n;
////        $this->password = $p;
////        $this->avatar = $a;
////        $this->continuousDays = $cd;
////        $this->goldCoinAmount = $ga;
////        $this->goldCoinToday = $gt;
////    }
//
//    public User() {
//        // username == -1 说明没有找到
//        this.id = -1;
//    }
//
//    public User(int i, String n, String av, int a, int b, int c) {
//        this.id = i;
//        this.rank = -1;
//        this.name = n;
//        this.password = "No need to get the pwd";
//        this.avatar = av;
//        this.continuousDays = a;
//        this.goldCoinAmount = b;
//        this.goldCoinToday  = c;
//    }
//
//    public boolean register(String json) {
//        boolean RegisterOK = false;
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            if (jsonObject.getInt("statue_code") == 200) {
//                RegisterOK = true;
//            }
//
////            JSONObject data = jsonObject.getJSONObject("user");
////
////            //maybe no use
////            this.username = data.getInt("username");
////            this.name = data.getString("name");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return RegisterOK;
//    }
//
//    public User(String json) {
//        try {
//            // 如果没有json返回值，则这里会抛出异常并接住，
//            JSONObject jsonObject = new JSONObject(json);
//            JSONObject data = jsonObject.getJSONObject("user");
//
//            this.id = data.getInt("username");
//            this.rank = data.getInt("rank");
//            this.name = data.getString("name");
//            this.password = data.getString("password");
//            this.avatar = data.getString("avatar");
//            this.continuousDays = data.getInt("continuousDays");
//            this.goldCoinAmount = data.getInt("goldCoinAmount");
//            this.goldCoinToday = data.getInt("goldCoinToday");
//
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public boolean update(String json) {
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//            //TODO
//        }  catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }
//
//
//    static public List<User> getUserList(String json) {
//        List<User> userList = new ArrayList<>();
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//
//
//            JSONArray userArray = jsonObject.getJSONArray("user");
//            for (int i = 0; i < userArray.length(); ++ i) {
//                JSONObject tmp = userArray.getJSONObject(i);
//                User user = new User(tmp.toString());
//                userList.add(user);
//            }
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return userList;
//    }
//
//
//    @Override
//    public String toString() {
//        String tmp = "username: " + this.id + "\n";
//        tmp += ("name: " + this.name + "\n");
//        tmp += ("rand: " + (this.rank == -1 ? "No Rank!" : this.rank ) + "\n");
//        tmp += ("avatar: " + this.avatar + "\n");
//        tmp += ("continuousDays " + this.continuousDays + "\n");
//        tmp += ("goldCoinAmount " + this.goldCoinAmount + "\n");
//        tmp += ("goldCoinToday " + this.goldCoinToday + "\n");
//        return tmp;
//    }
//}

public class User {
    private int   id;
    private int    rank;
    private String name;
    private String password;
    private String avatar;
    private int    continuousDays;
    public  int    goldCoinAmount;
    public  int    goldCoinToday;

    @Override
    public String toString() {
        String tmp = "username: " + this.id + "\n";
        tmp += ("name: " + this.name + "\n");
        tmp += ("rand: " + (this.rank == -1 ? "No Rank!" : this.rank ) + "\n");
        tmp += ("avatar: " + this.avatar + "\n");
        tmp += ("continuousDays " + this.continuousDays + "\n");
        tmp += ("goldCoinAmount " + this.goldCoinAmount + "\n");
        tmp += ("goldCoinToday " + this.goldCoinToday + "\n");
        return tmp;
    }
}
