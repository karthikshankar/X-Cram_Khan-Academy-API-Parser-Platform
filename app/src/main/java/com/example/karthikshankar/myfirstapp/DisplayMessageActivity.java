package com.example.karthikshankar.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Callback;

public class DisplayMessageActivity extends AppCompatActivity {

    private WebView mWebView;
    private static Excercise[] excercises;

    /*
     * Created by karthikshankar 9/30/17
     */
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        mWebView = (WebView)findViewById(R.id.activity_display_courseWebPage);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

//        KhanAcademyParse kh = new KhanAcademyParse();
        MainActivity m = new MainActivity();
        ArrayList<Excercise> excerciseList = null;
        try {
            loadData(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData (String topic) throws Exception {
        String url = "https://www.khanacademy.org/api/v1/exercises" ;
        run(url, topic);
    }

    public void run(String url, final String topic) throws IOException {
        final ArrayList<Excercise> excerciseList = new ArrayList<Excercise>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("APP", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                excercises = gson.fromJson(response.body().string(),Excercise[].class);
                for (Excercise e: excercises) {
                    if (e.getName().contains(topic)) {
                        excerciseList.add(e);
                    }
                }
                DisplayMessageActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl(excerciseList.get(0).getUrl());
                    }
                });
            }
        });
    }

}