package com.example.auth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    EditText inputEmail, inputPass, inputConfirmPass, inputName;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        inputConfirmPass = findViewById(R.id.inputConfirmPass);
        signup = findViewById(R.id.registerBtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        signup.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }


    private void PerforAuth(){
        String email = inputEmail.getText().toString();
        String name = inputName.getText().toString();
        String pass = inputPass.getText().toString();
        String confirm = inputConfirmPass.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter valid Email");
        }
        else if(pass.isEmpty() || pass.length() < 6) {
            inputPass.setError("Enter Proper Password");
        }
        else if(!pass.equals(confirm)){
            inputConfirmPass.setError("Password not match both field");
        }
        /*else if(!name.matches(namePattern)){
            inputName.setError("Enter valid name");
        }*/
        else{
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }

                private void sendUserToNextActivity() {
                    Intent intent = new Intent(Register.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });


        }

    }





    public void loginBtn(View view) {
        Intent intent = new Intent(Register.this,Login.class);
        startActivity(intent);
    }

    public void info(View view) {
        Intent intent = new Intent(this,info.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void user(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }


}