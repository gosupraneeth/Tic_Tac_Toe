package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    int gameState;
    Button button1;
    Typeface tfc1;
    private int player1Points;
    private TextView textviewplayer1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button1=(Button) findViewById(R.id.happyPlay);
        tfc1= Typeface.createFromAsset(getAssets(),"fonts/borque.ttf");
        button1.setTypeface(tfc1);
        textviewplayer1 =findViewById(R.id.text_view_p1);
        gameState=1; //1-start 2-gameover 3-draw
        Button buttonreset =findViewById(R.id.button_reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetGame();
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

    public void GameBordClick(View view) {
        ImageView selectedImage = (ImageView)  view;
        int selectedBlock=0;
        switch (selectedImage.getId())
        {
            case R.id.button_00: selectedBlock=1;break;
            case R.id.button_01: selectedBlock=2;break;
            case R.id.button_02: selectedBlock=3;break;
            case R.id.button_10: selectedBlock=4;break;
            case R.id.button_11: selectedBlock=5;break;
            case R.id.button_12: selectedBlock=6;break;
            case R.id.button_20: selectedBlock=7;break;
            case R.id.button_21: selectedBlock=8;break;
            case R.id.button_22: selectedBlock=9;break;
        }
        PlayGame(selectedBlock,selectedImage);
    }
    int activePlayer =1;
    ArrayList<Integer> player1 =new ArrayList<Integer>();
    ArrayList<Integer> player2 =new ArrayList<Integer>();
    private void PlayGame(int selectedBlock, ImageView selectedImage) {
        if (gameState==1){
            if (activePlayer==1){
                selectedImage.setImageResource(R.drawable.tictactoe_x2);
                player1.add(selectedBlock);
                activePlayer=2;
                CheckWinner();
                AutoPlay();
            }
            else if (activePlayer==2){
                selectedImage.setImageResource(R.drawable.tictactoe_o2);
                player2.add(selectedBlock);
                activePlayer=1;
                CheckWinner();
            }
            selectedImage.setEnabled(false);

        }
    }

    private void AutoPlay() {
        ArrayList<Integer> emptyBlocks= new ArrayList<Integer>();

        for (int i=1;i<=9;i++){
            if (!(player1.contains(i) || player2.contains(i))){
                emptyBlocks.add(i);
            }

        }

        if (emptyBlocks.size()==0){
            CheckWinner();
            if (gameState==1){
                AlertDialog.Builder b=new AlertDialog.Builder(this , android.R.style.Theme_Material_Dialog);
                Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();

            }
            gameState=3;
        }else {
            Random r= new Random();
            int randomIndex = r.nextInt(emptyBlocks.size());
            int selectedBlock =emptyBlocks.get(randomIndex);

            ImageView selectedImage =(ImageView) findViewById(R.id.button_00);
            switch (selectedBlock){
                case 1: selectedImage =(ImageView) findViewById(R.id.button_00); break;
                case 2: selectedImage =(ImageView) findViewById(R.id.button_01); break;
                case 3: selectedImage =(ImageView) findViewById(R.id.button_02); break;

                case 4: selectedImage =(ImageView) findViewById(R.id.button_10); break;
                case 5: selectedImage =(ImageView) findViewById(R.id.button_11); break;
                case 6: selectedImage =(ImageView) findViewById(R.id.button_12); break;

                case 7: selectedImage =(ImageView) findViewById(R.id.button_20); break;
                case 8: selectedImage =(ImageView) findViewById(R.id.button_21); break;
                case 9: selectedImage =(ImageView) findViewById(R.id.button_22); break;
            }
            PlayGame(selectedBlock,selectedImage);
        }
    }
    private  void updatePointsText() {
        textviewplayer1.setText("PLAYER = "+player1Points);
    }


    void ResetGame(){
        gameState=1;
        activePlayer=1;
        player1.clear();
        player2.clear();

        ImageView iv;
        iv =(ImageView) findViewById(R.id.button_00); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_01); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_02); iv.setImageResource(0); iv.setEnabled(true);

        iv =(ImageView) findViewById(R.id.button_10); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_11); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_12); iv.setImageResource(0); iv.setEnabled(true);

        iv =(ImageView) findViewById(R.id.button_20); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_21); iv.setImageResource(0); iv.setEnabled(true);
        iv =(ImageView) findViewById(R.id.button_22); iv.setImageResource(0); iv.setEnabled(true);
    }

    private void CheckWinner() {
        int winner = 0;
        if (player1.contains(1)&&player1.contains(2)&&player1.contains(3)){ winner=1; }
        if (player1.contains(4)&&player1.contains(5)&&player1.contains(6)){ winner=1; }
        if (player1.contains(7)&&player1.contains(8)&&player1.contains(9)){ winner=1; }

        if (player1.contains(1)&&player1.contains(4)&&player1.contains(7)){ winner=1; }
        if (player1.contains(2)&&player1.contains(5)&&player1.contains(8)){ winner=1; }
        if (player1.contains(3)&&player1.contains(6)&&player1.contains(9)){ winner=1; }

        if (player1.contains(1)&&player1.contains(5)&&player1.contains(9)){ winner=1; }
        if (player1.contains(3)&&player1.contains(5)&&player1.contains(7)){ winner=1; }




        if (player2.contains(1)&&player2.contains(2)&&player2.contains(3)){ winner=2; }
        if (player2.contains(4)&&player2.contains(5)&&player2.contains(6)){ winner=2; }
        if (player2.contains(7)&&player2.contains(8)&&player2.contains(9)){ winner=2; }

        if (player2.contains(1)&&player2.contains(4)&&player2.contains(7)){ winner=2; }
        if (player2.contains(2)&&player2.contains(5)&&player2.contains(8)){ winner=2; }
        if (player2.contains(3)&&player2.contains(6)&&player2.contains(9)){ winner=2; }

        if (player2.contains(1)&&player2.contains(5)&&player2.contains(9)){ winner=2; }
        if (player2.contains(3)&&player2.contains(5)&&player2.contains(7)){ winner=2; }


        if(winner !=0 && gameState ==1){
            if(winner ==1){
                player1Points++;
                Toast.makeText(this,"You Won the Game",Toast.LENGTH_SHORT).show();
                updatePointsText();
            }else if (winner==2){
                Toast.makeText(this,"You Lost the Game",Toast.LENGTH_SHORT).show();
            }gameState=2;

        }
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("player1Points",player1Points);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1Points=savedInstanceState.getInt("player1Points");

    }
}
