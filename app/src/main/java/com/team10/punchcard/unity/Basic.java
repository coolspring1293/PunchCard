package com.team10.punchcard.unity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by liuw53 on 5/16/16.
 */
public class Basic {
    static String name;
    static String res;
    static Word mWord;



    Handler mHandler=new Handler() {
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

    public Basic(String _name) {
        name = _name;

    }

    public void getJson(String urlString) {
        final HttpUrlConnection httpUrlConnection = new HttpUrlConnection(mHandler, urlString);
        httpUrlConnection.getJsonFromInternet();

        res = httpUrlConnection.getResult();


    }

    public String getName() {
        return name;
    }

    public String getRes() {
        if (res == null) return "Invalid URL";
        return res;
    }



    public List<SingleUser> getFriendList() {
        List<SingleUser> l = new ArrayList<>();

        return l;
    }


    public class SingleUser {
        private Long id;
        private String name;
        private int continuousDays;
        private int goldCoinCount;

        public SingleUser() {
            this.id = null;
            this.name = "No Name";
            this.continuousDays = 1;
            this.goldCoinCount = 0;
        }

    }


    public Word getWord() {
        return mWord;
    }


    public void decodeJsonToWord(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            List<String> en_definitions = new ArrayList<>();
            String msg = "Invivad";
            int status = 0;
            String uk = "uk", us = "us";

            msg = jsonObject.getString("msg");
            status = jsonObject.getInt("status_code");

            JSONObject data = jsonObject.getJSONObject("data");

                JSONObject pronunciations = data.getJSONObject("pronunciations");
                    uk = pronunciations.getString("uk");
                    us = pronunciations.getString("us");

            JSONObject definitions = data.getJSONObject("en_definitions");
            JSONArray  v = definitions.getJSONArray("v");
            for (int k = 0; k < v.length(); ++ k) {
                int index = k + 1;
                en_definitions.add(index + ". " + v.getString(k));
            }
            Log.v("IFO", msg + status + uk + us);
            Word w = new Word(msg, status, uk, us, en_definitions);
            mWord = w;


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public class Word {
        int id;
        String msg;
        String status;
        String uk_pronunciations, us_pronunciations;
        List<String> en_definitions;

        public Word(String m, int s, String u, String a, List<String> d) {
            this.status = "Successfully";
            this.msg = m;
            this.id = s;
            this.us_pronunciations = a;
            this.us_pronunciations = u;
            this.en_definitions = d;
        }

        @Override
        public String toString() {
            String tmp = "ID:" + id + "\t" + status + "\nPronunciations:";
            tmp += (uk_pronunciations + "\t" + us_pronunciations + "\nMeaning:\n");

            for (String i : en_definitions) {
                tmp += (i + "\n");
            }
            return tmp;
        }
    }


}
