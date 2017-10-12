package com.example.karthikshankar.myfirstapp;

import com.example.karthikshankar.myfirstapp.Excercise;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class KhanAcademyParse {

    private static Excercise[] excercises;

    public KhanAcademyParse() {

    }

    public static ArrayList<Excercise> loadData (String topic) throws Exception {
        ArrayList<Excercise> excerciseList = new ArrayList<Excercise>();
        Gson gson = new Gson();
        String url = "kirby.ngrok.io/exercises?query=" + topic;
        String json = run(url);
        excercises = gson.fromJson(json,Excercise[].class);
        for (Excercise e: excercises) {
            if (e.getName().contains(topic)) {
                excerciseList.add(e);
            }
        }
        return excerciseList;

    }
    public static String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
