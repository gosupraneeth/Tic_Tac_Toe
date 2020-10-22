package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void startGame_SinglePlayer(View view) {
        Intent i=new Intent(this,GameActivity.class);
        startActivity(i);

    }

    public void EndGame(View view) {
        int pid =android.os.Process.myPid();
        android.os.Process.killProcess(pid);

    }

    public void ShowAboutNote(View view) {
        Intent i =new Intent(this, AboutActivity.class);
        startActivity(i);
    }


    public void StartGameMultiplayer(View view) {
        Intent i =new Intent(this, Game2Activity.class);
        startActivity(i);
    }

    public void StartOnlineGame(View view) {
        Intent i =new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}