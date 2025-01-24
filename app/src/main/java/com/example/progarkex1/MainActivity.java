package com.example.progarkex1;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.progarkex1.tasks.Task2;

public class MainActivity extends AppCompatActivity {

    private static Context gameContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;
        EdgeToEdge.enable(this);
        setContentView(new Task2(this));
    }

    public static Context getGameContext() {
        return gameContext;
    }
}