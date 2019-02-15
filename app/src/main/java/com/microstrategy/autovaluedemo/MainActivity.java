package com.microstrategy.autovaluedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvName = findViewById(R.id.tv_name);
        Gson gson = gson();
        Teacher teacher = gson.fromJson("{\"list\":[\"1\"],\"map\":{\"key\":\"123\"}}", Teacher.class);
        tvName.setText(teacher.toString());

    }

    private Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new ImmutableMapTypeAdapterFactory());
        gsonBuilder.registerTypeAdapterFactory(new ImmutableListTypeAdapterFactory());
        return gsonBuilder.registerTypeAdapterFactory(MyAdapterFactory.create()).create();
    }


}
