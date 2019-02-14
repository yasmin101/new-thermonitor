package com.example.pc.newthermonitor;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class ListActivity extends AppCompatActivity {
    ListView ListView;
    int[] images = {R.drawable.android,R.drawable.iphone,R.drawable.wi ,R.drawable.blackberry,R.drawable.linux};
    String[] Names = {"android", "iphone", "windows", "blackberry", "linux"};
//    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        FirebaseApp.initializeApp(this);
//        firebaseAuth = FirebaseAuth.getInstance();
        ListView = (ListView) findViewById(R.id.ListView);
        CustomAdapter customAdapter = new CustomAdapter();
        ListView.setAdapter(customAdapter);
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DeviceDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row, null);
            TextView textview = (TextView) view1.findViewById(R.id.textView);
            ImageView imageView = (ImageView) view1.findViewById(R.id.imageView);
            imageView.setImageResource(images[i]);
            textview.setText(Names[i]);

            return view1;
        }
    }


}