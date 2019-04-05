package com.example.pc.newthermonitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeviceDetailActivity extends AppCompatActivity {

    private String author;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

// Read from the database

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("attt", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("dd", "Failed to read value.", error.toException());
            }

            });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                DeviceDetailActivity newPost = dataSnapshot.getValue(DeviceDetailActivity.class);
                System.out.println("Previous Post ID: " + prevChildKey);
            }
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                DeviceDetailActivity changedPost = dataSnapshot.getValue(DeviceDetailActivity.class);
                System.out.println("The updated post title is: " + changedPost.title);
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                DeviceDetailActivity removedPost = dataSnapshot.getValue(DeviceDetailActivity.class);
                System.out.println("The blog post titled " + removedPost.title + " has been deleted");
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


    }


}
