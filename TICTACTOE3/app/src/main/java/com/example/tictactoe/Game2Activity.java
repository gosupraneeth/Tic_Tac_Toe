package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Game2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] button =new Button[3][3];
    private boolean player1Turn =true;
    private  int roundCount;
    private int player1Points;
    private  int player2Points;
    private TextView textviewplayer1;
    private TextView textviewplayer2;
    private int count=1;
    Button button1;
    Typeface tfc1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        button1=(Button) findViewById(R.id.happyPlay);
        tfc1= Typeface.createFromAsset(getAssets(),"fonts/borque.ttf");
        button1.setTypeface(tfc1);
        textviewplayer1 =findViewById(R.id.text_view_p1);
        textviewplayer2 =findViewById(R.id.text_view_p2);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonId = "button_"+i+j;
                int resId=getResources().getIdentifier(buttonId, "id",getPackageName());
                button[i][j]=findViewById(resId);
                button[i][j].setOnClickListener(this);
            }
        }
        Button buttonreset =findViewById(R.id.button_reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button buttonmenu =findViewById(R.id.button_menu);
        buttonmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid =android.os.Process.myPid();
                android.os.Process.killProcess(pid);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        if(count%2==1) {
            if (player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
        }
        else{

            if (!player1Turn) {
                ((Button) v).setText("X");
            } else {
                ((Button) v).setText("O");
            }
        }
        roundCount++;
        if(checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        }
        else if(roundCount==9){
            draw();
        }
        else{
            player1Turn=!player1Turn;
        }


    }
    //  button.setBackgroundColor();
    private boolean checkForWin(){
        String[][] field =new String[3][3];
        for(int i=0;i<3; i++){
            for (int j=0;j<3;j++){
                field[i][j]=button[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals("")){
                return true;
            }
        }
        for(int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals("")){
            return true;
        }
        if(field[2][0].equals(field[1][1])&&field[2][0].equals(field[0][2])&&!field[2][0].equals("")){
            return true;
        }
        return  false;
    }
    private void player1Wins() {
        player1Points++;
        Toast.makeText(this,"Player 1 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private  void player2Wins()  {
        player2Points++;
        Toast.makeText(this,"Player 2 wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void  draw() {
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }
    private  void updatePointsText() {
        textviewplayer1.setText("PLAYER 1 = "+player1Points);
        textviewplayer2.setText("PLAYER 2 = "+player2Points);
    }
    private  void resetBoard() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                button[i][j].setText("");
            }
        }
        roundCount =0;
        count++;
        if(count%2==1) {
            player1Turn = true;
            Toast.makeText(this,"Player 1 turn",Toast.LENGTH_LONG).show();
        }
        else{
            player1Turn=false;
            Toast.makeText(this,"Player 2 turn",Toast.LENGTH_LONG).show();
        }
    }
    private  void resetGame() {
        player1Points=0;
        player2Points=0;
        count=0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount=savedInstanceState.getInt("roundCount");
        player1Points=savedInstanceState.getInt("player1Points");
        player2Points=savedInstanceState.getInt("player2Points");
        player1Turn=savedInstanceState.getBoolean("player1Turn");
    }
}