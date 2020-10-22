package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Game3Activity extends AppCompatActivity {
    Button[][] button =new Button[3][3];
    boolean player1Turn =true;
    int roundCount=-1;
    int player1Points=0;
    int player2Points=0;
    TextView textviewplayer1;
    TextView textviewplayer2;
    int count=1;
    Button buttonName;


    String playerName = "";
    String roomName = "";
    String role = "";
    String message = "";
    String buttonString = "";

    FirebaseDatabase database;
    DatabaseReference messageRef;
    Button button1;
    Typeface tfc1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
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
            }
        }



        database = FirebaseDatabase.getInstance();

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        playerName = preferences.getString("playerName", "");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            roomName = extras.getString("roomName");
            if (roomName.equals(playerName)) {
                role = "host";
            } else {
                role = "guest";
            }
        }

        button[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[0][0].getText().toString().equals("")) {
                    button[0][0].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_00";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });
        button[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[0][1].getText().toString().equals("")) {
                    button[0][1].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_01";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });
        button[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[0][2].getText().toString().equals("")) {
                    button[0][2].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_02";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });


        button[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[1][0].getText().toString().equals("")) {
                    button[1][0].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_10";
                        messageRef.setValue(message);
                    }
                }
                else {
                    return;
                }
            }
        });
        button[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[1][1].getText().toString().equals("")) {
                    button[1][1].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_11";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });
        button[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[1][2].getText().toString().equals("")) {
                    button[1][2].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_12";
                        messageRef.setValue(message);
                    }
                }
                else {
                    return;
                }
            }
        });


        button[2][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[2][0].getText().toString().equals("")) {
                    button[2][0].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_20";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });
        button[2][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[2][1].getText().toString().equals("")) {
                    button[2][1].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_21";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });
        button[2][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send message
                if(button[2][2].getText().toString().equals("")) {
                    button[2][2].setText("X");
                    roundCount=roundCount+2;
                    if (checkForWin()) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        player1Points++;
                        roundCount=-1;
                        textviewplayer1.setText("PLAYER 1 = "+player1Points);
                        textviewplayer2.setText("PLAYER 2 = "+player2Points);
                        message = role + ":You Lost";
                        messageRef.setValue(message);
                    } else if (roundCount == 9) {
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setText("");
                            }
                        }
                        roundCount=-1;
                        message =role + ":Draw";
                        messageRef.setValue(message);
                    } else {

                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(false);
                            }
                        }
                        message = role + ":button_22";
                        messageRef.setValue(message);
                    }
                }
                else{
                    return;
                }
            }
        });

        Button buttonreset =findViewById(R.id.button_reset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        button[i][j].setText("");
                    }
                }
                player1Points=0;
                player2Points=0;
                roundCount=-1;
                updatePointsText();
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

        //listen for incoming messages
        messageRef = database.getReference("rooms/" + roomName + "/message");
        message = role + ":pressed";
        messageRef.setValue(message);
        addRoomEventListener();
    }
    private void addRoomEventListener(){
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //message-received
                if (role.equals("host")){
                    if (dataSnapshot.getValue(String.class).contains("guest:")){
                        buttonString=dataSnapshot.getValue(String.class).replace("guest:","");
                        if((!buttonString.equals("pressed"))&&(!buttonString.equals("You Lost"))&&(!buttonString.equals("Draw"))) {
                            int resId = getResources().getIdentifier(buttonString, "id", getPackageName());
                            buttonName = findViewById(resId);

                            buttonName.setText("O");
                        }
                        else if(buttonString.equals("You Lost")){
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                    button[i][j].setText("");
                                }
                            }
                            player2Points++;
                            textviewplayer1.setText("PLAYER 1 = "+player1Points);
                            textviewplayer2.setText("PLAYER 2 = "+player2Points);
                            roundCount=-1;

                        }
                        else if(buttonString.equals("Draw")){
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                    button[i][j].setText("");
                                }
                            }
                            roundCount=-1;
                        }
                        for(int i=0;i<3;i++){
                            for(int j=0;j<3;j++){
                                button[i][j].setEnabled(true);
                            }
                        }
                        Toast.makeText(Game3Activity.this,""+
                                dataSnapshot.getValue(String.class),Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    if (dataSnapshot.getValue(String.class).contains("host:")) {
                        buttonString=dataSnapshot.getValue(String.class).replace("host:", "");
                        if ((!buttonString.equals("pressed"))&&(!buttonString.equals("You Lost"))&&(!buttonString.equals("Draw"))) {
                            int resId = getResources().getIdentifier(buttonString, "id", getPackageName());
                            buttonName = findViewById(resId);

                            buttonName.setText("O");
                        }
                        else if(buttonString.equals("You Lost")){
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                    button[i][j].setText("");
                                }
                            }
                            player2Points++;
                            textviewplayer1.setText("PLAYER 1 = "+player1Points);
                            textviewplayer2.setText("PLAYER 2 = "+player2Points);
                            roundCount=-1;

                        }
                        else if(buttonString.equals("Draw")){
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                    button[i][j].setText("");
                                }
                            }
                            roundCount=-1;
                        }
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                button[i][j].setEnabled(true);
                            }
                        }

                        Toast.makeText(Game3Activity.this, "" +
                                dataSnapshot.getValue(String.class), Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //error-retry
                messageRef.setValue(message);
            }
        });
    }

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

        updatePointsText();
        resetBoard();
    }
    private  void player2Wins()  {

        player2Points++;

        updatePointsText();
        resetBoard();
    }
    private void  draw() {


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