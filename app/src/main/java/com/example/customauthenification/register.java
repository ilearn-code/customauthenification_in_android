package com.example.customauthenification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText firstname,lastname,email,password;
    Button register,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.reg);
        login = findViewById(R.id.log);

        register.setOnClickListener(view -> {
            createUser();
        });
        login.setOnClickListener(view -> {
            startActivity(new Intent(register.this, login.class));

        });
    }
        private void createUser()
        {
            String fn=firstname.getText().toString();
            String ln=lastname.getText().toString();
            String em=email.getText().toString();
            String pas=password.getText().toString();

            if(TextUtils.isEmpty(fn)){
                firstname.setError(" Name cannot be empty");
                firstname.requestFocus();
            }
            else if(TextUtils.isEmpty(ln)){
            lastname.setError(" Name cannot be empty");
            lastname.requestFocus();
        }
            else if(TextUtils.isEmpty(em)){
            email.setError(" Name cannot be empty");
            email.requestFocus();
        }
            else if (TextUtils.isEmpty(pas)){
            password.setError(" Name cannot be empty");
            password.requestFocus();
        }
            else
            {
             mAuth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(register.this," user registered successfully ",Toast.LENGTH_LONG).show();
                     }
                     else
                     {
                         Toast.makeText(register.this," Registeration error"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                     }
                 }
             });
            }


}
}
