package com.example.panaspornkitipong.facetendance;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    // Define unique name from layout

    private EditText memailsignupField;
    private EditText mpasswordsignupField;
    private Button msignup_btn;
    private TextView msigninhere;
    private ProgressDialog progressDialog; // For running time

    // Define Firebase

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);

        // Define findViewById

        memailsignupField = (EditText) findViewById(R.id.emailsignupField);
        mpasswordsignupField = (EditText) findViewById(R.id.passwordsignupField);
        msignup_btn = (Button) findViewById(R.id.signup_btn);
        msigninhere = (TextView) findViewById(R.id.signinhere);

        // Set Click ( Like to another layout )

        msignup_btn.setOnClickListener(this);
        msigninhere.setOnClickListener(this);

        // Firebase
        firebaseAuth = FirebaseAuth.getInstance();

    }

    // Function Register
    private void registerUser() {

        String email = memailsignupField.getText().toString().trim();
        String password = mpasswordsignupField.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            // Email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            // Password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }


        // If validations are Ok , we will first show a progressbar

        progressDialog.setMessage("User Authentication");
        progressDialog.show();

        // Firebase Authentication (CreateUser on Firebase Authentication)

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //user is successfully registered and logged in
                    //we will start the home activity here
                    //right now lets display a toast only
                    Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Unsuccessfully Sign Up
                    Toast.makeText(SignUpActivity.this, "Could not register. Please Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == msignup_btn) {
            registerUser();
        }

        if (view == msigninhere) {

        }
    }
}
