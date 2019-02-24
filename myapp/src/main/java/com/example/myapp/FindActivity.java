package com.example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class FindActivity extends AppCompatActivity {
private Toolbar mytoobar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        mytoobar = findViewById(R.id.mytoobar);
        //logo
        mytoobar.setLogo(R.mipmap.ic_launcher);
        //主标题
        mytoobar.setTitle("Title");
        //副标题
        mytoobar.setSubtitle("Sub Title");
        //设置toobar
        //getSupportActionBar(mytoobar);
    }
}
