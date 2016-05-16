package com.team10.punchcard.unity;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;

/**
 * Created by liuw53 on 5/16/16.
 */
public class HttpUrlConnection {

    private String url;
    public static final int PARSESUCCWSS = 0x2001;
    private Handler handler;
    private String result;

    public HttpUrlConnection(Handler handler, String urlString) {
        // TODO Auto-generated constructor stub
        this.handler = handler;
        this.url = urlString;
    }

    public String getResult() {
        return result;
    }


    public void getJsonFromInternet() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200) {
                        InputStream inputStream = conn.getInputStream();
                        result = parseJson(inputStream);
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }


    protected String parseJson(InputStream inputStream) {
        // TODO Auto-generated method stub
        byte[] jsonBytes = convertIsToByteArray(inputStream);
        String json = new String(jsonBytes);
        return json;
    }

    private byte[] convertIsToByteArray(InputStream inputStream) {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        int length = 0;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            inputStream.close();
            baos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
