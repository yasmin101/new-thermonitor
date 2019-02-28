package com.example.pc.newthermonitor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSignin;
    private EditText Email1;
    private EditText Password1;
    private TextView Signup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    String TAG= "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),devicelistActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        buttonSignin = (Button) findViewById(R.id.button);
        Email1 = (EditText)findViewById(R.id.email);
        Password1=(EditText)findViewById(R.id.password);
        Signup=(TextView)findViewById(R.id.Signup);
        buttonSignin.setOnClickListener(this);
        Signup.setOnClickListener(this);
    }
    private void userlogin() {
        String email = Email1.getText().toString().trim();
        String password = Password1.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            // stopping function execution further
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        //if email and password are empty , show progressdialogue

        // progressDialog.setMessage("Registering...");
       // progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              //  progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),devicelistActivity.class));
                }
                else{
                    Log.i(TAG, "onComplete: ", task.getException());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==buttonSignin){
            userlogin();
        }
        if(view==Signup){
            startActivity(new Intent(this,RegisterActivity.class));

        }
    }
}

