package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RoomCreateActivity extends AppCompatActivity {

    ListView listview;
    Button button;

    List<String> roomsList;

    String playerName="";
    String roomName="";

    FirebaseDatabase database;
    DatabaseReference roomRef;
    DatabaseReference roomsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        database= FirebaseDatabase.getInstance();
        //get the player Name and assign his room name to the player name
        SharedPreferences preferences= getSharedPreferences("PREFS",0);
        playerName=preferences.getString("playerName","");
        roomName=playerName;

        listview=findViewById(R.id.listview);
        button=findViewById(R.id.button);

        //
        roomsList=new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating room and yourself as player1
                button.setText("CREATING ROOM");
                button.setEnabled(false);
                roomName= playerName;
                roomRef =database.getReference("roomName/"+roomName+"/player1");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                roomName= roomsList.get(position);
                roomRef =database.getReference("roomName/"+roomName+"/player2");
                addRoomEventListener();
                roomRef.setValue(playerName);
            }
        });
        //shows if new room is available
        addRoomsEventListener();

    }
    private void addRoomEventListener(){
        roomRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //join the room
                button.setText("CREATE ROOM");
                button.setEnabled(true);
                Intent intent= new Intent(getApplicationContext(), Game3Activity.class);
                intent.putExtra("roomName",roomName);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //error
                button.setText("CREATE ROOM");
                button.setEnabled(true);
                Toast.makeText(RoomCreateActivity.this, "Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addRoomsEventListener(){
        roomsRef= database.getReference("rooms");
        roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //show rooms list
                roomsList.clear();
                Iterable<DataSnapshot> rooms = dataSnapshot.getChildren();
                for (DataSnapshot snapshot:rooms){
                    roomsList.add(snapshot.getKey());

                    ArrayAdapter<String> adapter= new ArrayAdapter<>(RoomCreateActivity.this,
                            android.R.layout.simple_list_item_1, roomsList);
                    listview.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Error - nothing
            }
        });
    }

    public void Help(View view) {
        Intent i =new Intent(this, HelpActivity.class);
        startActivity(i);
    }
}