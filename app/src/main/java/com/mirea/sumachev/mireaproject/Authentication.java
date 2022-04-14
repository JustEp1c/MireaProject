package com.mirea.sumachev.mireaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText emailField;
    private EditText passwField;
    private Button signInBtn;
    private Button createAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_auth);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {

            }
            else {

            }
        };

        emailField = findViewById(R.id.editEmailAuth);
        passwField = findViewById(R.id.editPassw);
        signInBtn = findViewById(R.id.signInBtn);
        createAccBtn = findViewById(R.id.createAccBtn);

    }

    public void onClickSignIn(View view) {

        signIn(emailField.getText().toString(), passwField.getText().toString());
    }

    public void onClickCreateAcc(View view) {

        register(emailField.getText().toString(), passwField.getText().toString());
    }

    public void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Authentication.this, "Вы успешно авторизовались", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Authentication.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(Authentication.this, "Авторизация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register (String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(Authentication.this, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Authentication.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}