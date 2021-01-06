package com.example.itc327w_bookah_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.AppUtilities.AppUtility;
import com.example.itc327w_bookah_mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    //Variables
    TextInputLayout reset_email;
    TextInputEditText et_resetEmail;
    Button btnReset;
    TextView tvRememberedPassword;

    //Firebase
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //Hooks
        reset_email = findViewById(R.id.reset_email);
        et_resetEmail = findViewById(R.id.et_resetEmail);
        btnReset = findViewById(R.id.btnReset);
        tvRememberedPassword = findViewById(R.id.tvRememberedPassword);

        //Firebase
        auth = FirebaseAuth.getInstance() ;

        et_resetEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    reset_email.setError(null);
                }
                else
                {
                    if(!AppUtility.getInputText(et_resetEmail).isEmpty())
                    {
                        if(!AppUtility.isValidEmail(AppUtility.getInputText(et_resetEmail)))
                        {
                            et_resetEmail.setText("");
                            reset_email.setError("Invalid Email Format!");
                        }
                    }
                    else
                    {
                        reset_email.setError(getString(R.string.email_error));
                    }
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetPassword();
            }
        });

    }

    private void resetPassword()
    {
        String email = et_resetEmail.getText().toString().trim();

        if (email.isEmpty())
        {
            et_resetEmail.requestFocus();
            reset_email.setError("Please provide an email!");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            et_resetEmail.requestFocus();
            reset_email.setError("Please provide a valid email");
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this,
                                    "Check your email for the link to reset your password", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(ForgotPassword.this,
                                     "Try again, Something went wrong!", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}