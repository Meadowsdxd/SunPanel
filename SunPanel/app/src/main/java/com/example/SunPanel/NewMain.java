package com.example.SunPanel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NewMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);
    }
    public void spisok(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void totable(View view){
        Intent intent = new Intent(this, Popitka.class);
        startActivity(intent);
    }
}
