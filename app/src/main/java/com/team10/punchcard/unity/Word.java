package com.team10.punchcard.unity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuw53 on 5/16/16.
 */

/*
// TODO(by Rose), the following just is a sample!
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

    // Convert json(String) to a object
    public Word(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            List<String> mEn_definitions = new ArrayList<>();
            String mMsg = "Invivad";
            int mStatus = 0;
            String mUk = "uk", mUs = "us";

            mMsg = jsonObject.getString("msg");
            mStatus = jsonObject.getInt("status_code");

            JSONObject data = jsonObject.getJSONObject("data");

            JSONObject pronunciations = data.getJSONObject("pronunciations");
            mUk = pronunciations.getString("uk");
            mUk = pronunciations.getString("us");

            JSONObject definitions = data.getJSONObject("en_definitions");
            JSONArray v = definitions.getJSONArray("v");
            for (int k = 0; k < v.length(); ++ k) {
                int index = k + 1;
                mEn_definitions.add(index + ". " + v.getString(k));
            }
            this.status = "Successfully";
            this.msg = mMsg;
            this.id = mStatus;
            this.us_pronunciations = mUs;
            this.us_pronunciations = mUk;
            this.en_definitions = mEn_definitions;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
*/


public class Word {
    public String content;
    public String audio;
    public String definition;
    public String pronunciation;

    @Override
    public String toString() {
        return String.format("%s\t%s\n%s\n%s\n", content, pronunciation, definition, audio);
    }
}

