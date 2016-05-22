package com.team10.punchcard;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team10.punchcard.fake.FakeActivity;
import com.team10.punchcard.service.ShanbayService;
import com.team10.punchcard.service.ToastFailureCallback;
import com.team10.punchcard.service.pojo.WordResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsActivity extends Activity {

    private TitleView bar;
    private dbHelper helper;
    private Cursor cursor;
    private TextView tv_word;
    private TextView tv_meaning;
    private TextView tv_pron;
    private ImageView iv;
    private TextView another;
    private Button bn;
    private String TABLE_NAME = "TOEFL";
 //   private MediaPlayer mediaPlayer;
    private String url;
    int flag=0;

    ShanbayService shanbayService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words);
        bar = (TitleView) findViewById(R.id.title_view);

        bar.setTitleText(new String("背单词"));
        bar.setLeftButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("M_ACTION");
                startActivity(intent);
            }
        });


        shanbayService = new Retrofit.Builder().baseUrl(ShanbayService.END_POINT)
                .addConverterFactory(GsonConverterFactory.create()).build().create(ShanbayService.class);

        helper = new dbHelper(this);
/*        try {
            String path= "file:///android_asset/toefl1.txt";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String term;
            while((term=reader.readLine())!=null)
                helper.insert(term);
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/


        try {
            InputStream in = getResources().getAssets().open("toefl1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String term;
            while((term=reader.readLine())!=null)
                helper.insert(term);
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        cursor = helper.query();

        bn = (Button) findViewById(R.id.bt1);
        bn.setOnClickListener(new bnClickListener());
        tv_word = (TextView)findViewById(R.id.word);
        tv_meaning = (TextView)findViewById(R.id.meaning);
        tv_pron = (TextView)findViewById(R.id.pron);
        iv=(ImageView)findViewById(R.id.laba) ;
        iv.setOnClickListener(new ivClickListener());
        another = (TextView)findViewById(R.id.another);
        another.setOnClickListener(new tvClickListener());
        url=null;


        cursor.moveToNext();
        int nameColumnIndex = cursor.getColumnIndex("word");
        String word = cursor.getString(nameColumnIndex);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update "+TABLE_NAME+" set label=1 where word=\""+word+"\"";
        db.execSQL(sql);
        tv_word.setText(word);

        Call<WordResponse> call = shanbayService.getWord(word);
        call.enqueue(new ToastFailureCallback<WordResponse>(null, new IllegalArgumentException("Word not found.")) {
            @Override
            public void onSuccess(Call<WordResponse> call, Response<WordResponse> response) {
                tv_meaning.setText(response.body().data.definition);
                tv_pron.setText("/"+response.body().data.pronunciation+"/");
                url=response.body().data.audio;
            }
        });
    }

    class bnClickListener implements View.OnClickListener
    {
        public void onClick(View v) {

            if(flag==0) {
                if (cursor.moveToNext()) {
                    int nameColumnIndex = cursor.getColumnIndex("word");
                    String word = cursor.getString(nameColumnIndex);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String sql = "update " + TABLE_NAME + " set label=1 where word=\"" + word + "\"";
                    db.execSQL(sql);
                    tv_word.setText(word);

                    Call<WordResponse> call = shanbayService.getWord(word);
                    call.enqueue(new ToastFailureCallback<WordResponse>(v, new IllegalArgumentException("Word not found.")) {
                        @Override
                        public void onSuccess(Call<WordResponse> call, Response<WordResponse> response) {
                            tv_meaning.setText(response.body().data.definition);
                            tv_pron.setText("/" + response.body().data.pronunciation + "/");
                            url = response.body().data.audio;
                        }
                    });
               /* Snackbar.make(v, "Show: " + call.request().url(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

              /*  try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer
                            .setDataSource(url);

                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Log.v("AUDIOHTTPPLAYER", e.getMessage());
                }*/
                } else {
                    bn.setText("打卡");
                    another.setVisibility(View.VISIBLE);
                    cursor.close();
                    flag=1;
                }

            }
            else
            {
                Intent intent = new Intent();
                intent.setClass(WordsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    class tvClickListener implements View.OnClickListener
    {
        public void onClick(View v) {

            //      cursor = helper.query(3);
            cursor = helper.query();
            another.setVisibility(View.INVISIBLE);
            bn.setText("下一个");
            flag=0;

        }
    }

    class ivClickListener implements View.OnClickListener {
        public void onClick(View v) {

            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer
                        .setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                Log.v("AUDIOHTTPPLAYER", e.getMessage());
            }
        }
    }
}
