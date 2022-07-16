package com.example.customauthenification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
EditText eml,pal;
Button bl,rl;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eml=findViewById(R.id.el);
        pal=findViewById(R.id.pl);
        bl=findViewById(R.id.lbl);
        rl=findViewById(R.id.rl);
        mAuth = FirebaseAuth.getInstance();

        bl.setOnClickListener(view -> {
            loginUser();
        });
        rl.setOnClickListener(view -> {
            startActivity(new Intent(login.this, register.class));
        });
    }
    private void loginUser()
    {

        String em=eml.getText().toString();
        String pas=pal.getText().toString();


         if(TextUtils.isEmpty(em)){
            eml.setError(" Name cannot be empty");
            pal.requestFocus();
        }
        else if (TextUtils.isEmpty(pas)) {
             pal.setError(" Name cannot be empty");
             pal.requestFocus();
         }
        else
         {
             mAuth.signInWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(login.this," user login successfully ",Toast.LENGTH_LONG).show();
                         startActivity(new Intent(login.this,MainActivity.class));
                     }
                     else
                     {
                         Toast.makeText(login.this," login error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                     }
                 }
             });
         }
          }
    }
