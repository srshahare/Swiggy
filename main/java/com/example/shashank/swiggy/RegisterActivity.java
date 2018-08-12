package com.example.shashank.swiggy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private ImageView signUpImage;
    private EditText signUpUserName;
    private EditText signUpEmail;
    private EditText signUpPassword;
    private Button signUpByn;
    private TextView signUpText;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpImage = (ImageView) findViewById(R.id.sign_up_image);
        signUpUserName = (EditText) findViewById(R.id.input_name);
        signUpEmail = (EditText) findViewById(R.id.input_email);
        signUpPassword = (EditText) findViewById(R.id.input_password);
        signUpByn = (Button) findViewById(R.id.btn_signup);
        signUpText = (TextView) findViewById(R.id.link_login);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();

            }
        });

        signUpByn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                progressDialog.show();
                progressDialog.setTitle("Creating Account");
                progressDialog.setMessage("please wait...while we are creating your account");

                String name = signUpUserName.getText().toString();
                String email = signUpEmail.getText().toString();
                String pass = signUpPassword.getText().toString();

                if (!name.isEmpty() || !email.isEmpty() || !pass.isEmpty())
                {
                    SignUpAccount(name,email,pass);

                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "please fill all above details", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void SignUpAccount(String name, String email, String pass)
    {
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();

                    Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
                else
                {
                    progressDialog.hide();
                    Toast.makeText(RegisterActivity.this, "you got some error...please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}











