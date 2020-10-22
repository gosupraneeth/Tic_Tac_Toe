package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText editText;

    String playerName="";

    FirebaseDatabase database;
    DatabaseReference playerRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText=findViewById(R.id.editText);
        button=findViewById(R.id.button);

        database=FirebaseDatabase.getInstance();

        //checking if player exists and get reference
        SharedPreferences preferences= getSharedPreferences("PREFS",0);
        playerName=preferences.getString("playerName","");
        if(!playerName.equals("")) {
            playerRef=database.getReference("players/"+playerName);
            addEventListener();
            playerRef.setValue("");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logging th player in
                playerName=editText.getText().toString();
                editText.setText("");
                if(!playerName.equals("")){
                    button.setText("LOGGING IN");
                    button.setEnabled(false);
                    playerRef=database.getReference("players/"+playerName);
                    addEventListener();
                    playerRef.setValue("");
                }
            }
        });
    }

    private void addEventListener() {
        //reads from database
        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //success-continue to the next screen after saving playerName
                if (!playerName.equals("")){
                    SharedPreferences preferences= getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("playerName",playerName);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), RoomCreateActivity.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //error
                button.setText("LOG IN");
                button.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}