package com.example.shashank.swiggy;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private ImageView loginImage;
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginBtn;
    private TextView loginText;


    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);

        loginImage = (ImageView) findViewById(R.id.login_image);
        loginEmail = (EditText) findViewById(R.id.input_email);
        loginPassword = (EditText) findViewById(R.id.input_password);
        loginBtn = (Button) findViewById(R.id.btn_login);
        loginText = (TextView) findViewById(R.id.link_signup);

        mAuth = FirebaseAuth.getInstance();

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent SignUpIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(SignUpIntent);
                finish();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressDialog.show();
                progressDialog.setTitle("Logging You");
                progressDialog.setMessage("please wait...while we are logging you to your account");

                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

               if (!email.isEmpty() || !pass.isEmpty())
               {

                   LoginAccount(email,pass);

               }
               else
               {
                   progressDialog.dismiss();

                   Toast.makeText(LoginActivity.this, "please fill above details correctly", Toast.LENGTH_SHORT).show();
               }


            }
        });
    }

    private void LoginAccount(String email, String pass)
    {
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();

                if (task.isSuccessful())
                {
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                else
                {
                    progressDialog.hide();
                    Toast.makeText(LoginActivity.this, "you got some error...please try again later", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
