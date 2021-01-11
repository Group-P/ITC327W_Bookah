package com.example.itc327w_bookah_mobile.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.itc327w_bookah_mobile.AppUtilities.AppUtility;
import com.example.itc327w_bookah_mobile.Common.Common;
import com.example.itc327w_bookah_mobile.Model.User;
import com.example.itc327w_bookah_mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Register" ;
    //Variables
    Animation topAnimation;
    ImageView ivLogo;
    public TextInputLayout reg_email, reg_firstName, reg_lastName, reg_phoneNumber,reg_id, reg_password,reg_confirmPassword;
    public TextInputEditText et_regEmail, et_regFirstName, et_regLastName, et_regPhoneNumber, et_regId, et_regPassword, et_regConfirmPassword;
    public Button btnRegister;
    TextView tvAlreadyRegistered;

    //Firebase
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        //Hooks
        ivLogo =findViewById(R.id.register_logo);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        tvAlreadyRegistered = findViewById(R.id.tvAlreadyRegistered);
        tvAlreadyRegistered.setOnClickListener(this);

        reg_email = findViewById(R.id.reg_email);
        et_regEmail = findViewById(R.id.et_regEmail);

        reg_firstName = findViewById(R.id.reg_firstName);
        et_regFirstName = findViewById(R.id.et_regFirstName);

        reg_lastName = findViewById(R.id.reg_lastName);
        et_regLastName = findViewById(R.id.et_regLastName);

        reg_phoneNumber = findViewById(R.id.reg_phoneNumber);
        et_regPhoneNumber = findViewById(R.id.et_regPhoneNumber);

        reg_id = findViewById(R.id.reg_id);
        et_regId = findViewById(R.id.et_regId);

        reg_password = findViewById(R.id.reg_password);
        et_regPassword = findViewById(R.id.et_regPassword);

        reg_confirmPassword = findViewById(R.id.reg_confirmPassword);
        et_regConfirmPassword = findViewById(R.id.et_regConfirmPassword);

        //Firebase
        auth = FirebaseAuth.getInstance();

        //Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        ivLogo.startAnimation(topAnimation);

        //Functionality

       et_regFirstName.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_firstName.setError(null);
            }
        });

        et_regLastName.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_lastName.setError(null);
            }
        });

        et_regId.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_id.setError(null);
            }
            else
            {
                if(!AppUtility.getInputText(et_regId).isEmpty())
                {
                    if(!AppUtility.isValidIDNumber(AppUtility.getInputText(et_regId)))
                    {
                        et_regId.setText("");
                        reg_id.setError("Invalid ID Number Format!");
                    }
                }
                else
                {
                    reg_id.setError(getString(R.string.idNumber_error));
                }
            }
        });

        et_regEmail.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_email.setError(null);
            }
            else
            {
                if(!AppUtility.getInputText(et_regEmail).isEmpty())
                {
                    if(!AppUtility.isValidEmail(AppUtility.getInputText(et_regEmail)))
                    {
                        et_regEmail.setText("");
                        reg_email.setError("Invalid Email Format!");
                    }
                }
                else
                {
                    reg_email.setError(getString(R.string.email_error));
                }
            }
        });

        et_regPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_password.setError(null);
            }
            else
            {
                if(!AppUtility.getInputText(et_regPassword).isEmpty())
                {
                    if(!AppUtility.isValidPassword(AppUtility.getInputText(et_regPassword)))
                    {
                        et_regPassword.setText("");
                        reg_password.setError("Password Too Simple");
                    }
                }
                else
                {
                    reg_password.setError(getString(R.string.password_error));
                }
            }
        });
        et_regConfirmPassword.setOnFocusChangeListener((view, hasFocus) -> {
            if(hasFocus)
            {
                reg_confirmPassword.setError(null);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAlreadyRegistered:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.btnRegister:
                    registerUser();
                break;
        }
    }

    public void registerUser()
    {
        String email = et_regEmail.getText().toString().trim();
        String firstName = et_regFirstName.getText().toString().trim();
        String lastName = et_regLastName.getText().toString().trim();
        String phoneNumber = et_regPhoneNumber.getText().toString().trim();
        String idNumber = et_regId.getText().toString().trim();
        String password = et_regPassword.getText().toString().trim();
        String confirm = et_regConfirmPassword.getText().toString().trim();

        //Email validation
        if (email.isEmpty())
        {
            et_regEmail.setText("");
            reg_email.setError("Please provide an email!");
            et_regEmail.requestFocus();
            return;
        }

        if (!AppUtility.isValidEmail(email))
        {
            et_regEmail.setText("");
            reg_email.setError("Please provide a valid email!");
            et_regEmail.requestFocus();
            return;
        }

        //First name validation
        if(firstName.isEmpty())
        {
            et_regFirstName.setText("");
            reg_firstName.setError("Please provide a first name!");
            et_regFirstName.requestFocus();

        }

        //Last name validation
        if(lastName.isEmpty())
        {
            et_regLastName.setText("");
            reg_lastName.setError("Please provide a last name!");
            et_regLastName.requestFocus();
            return;
        }

        //Phone number validation
        if (phoneNumber.isEmpty())
        {
            et_regPhoneNumber.setText("");
            reg_phoneNumber.setError("Please provide a phone number");
            et_regPhoneNumber.requestFocus();
            return;
        }

        if (!AppUtility.isValidCellphone(phoneNumber))
        {
            et_regPhoneNumber.setText("");
            reg_phoneNumber.setError("Invalid Cellphone Number!");
            et_regPhoneNumber.requestFocus();
            return;
        }

        //ID number validation
        if (idNumber.isEmpty())
        {
            et_regId.setText("");
            reg_id.setError("Please enter an ID number");
            et_regId.requestFocus();
            return;
        }
        if (!AppUtility.isValidIDNumber(idNumber))
        {
            et_regId.setText("");
            reg_id.setError("Please enter a valid RSA ID number");
            et_regId.requestFocus();
            return;
        }

        //Password validation
        if (password.isEmpty())
        {
            et_regPassword.setText("");
            reg_password.setError("Please provide a password");
            et_regPassword.requestFocus();
            return;
        }

        if (!AppUtility.isValidPassword(password))
        {
            et_regPassword.setText("");
            reg_password.setError("Password too simple");
            et_regPassword.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            et_regPassword.setText("");
            reg_password.setError("Password must be 6 characters or more ");
            et_regPassword.requestFocus();
            return;
        }

        if (!confirm.equals(password))
        {
            et_regPassword.setText("");
            et_regConfirmPassword.setText("");
            et_regPassword.requestFocus();
            et_regConfirmPassword.requestFocus();
            reg_password.setError("Password Mismatch!");
            reg_confirmPassword.setError("Password Mismatch!");
            return;
        }

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    User user = new User(
                                            et_regPhoneNumber.getText().toString().trim(),
                                            et_regFirstName.getText().toString().trim(),
                                            et_regLastName.getText().toString().trim(),
                                            et_regId.getText().toString().trim(),
                                            et_regEmail.getText().toString().trim(),
                                            et_regPassword.getText().toString().trim()

                                    );



                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful())
                                            {
                                                View toastView = getLayoutInflater().inflate(R.layout.toast, findViewById(R.id.toastLayout));
                                                AppUtility.ShowToast(getApplicationContext(), "Registration successful!\nPlease Login..." , toastView,1);

                                                //Redirect to the login screen
                                                startActivity(new Intent(Register.this,Login.class));
                                                finish();
                                            }
                                            else
                                            {
                                                View toastView = getLayoutInflater().inflate(R.layout.toast, findViewById(R.id.toastLayout));
                                                AppUtility.ShowToast(getApplicationContext(), "Failed to register! Try again..." , toastView,2);
                                            }
                                        }
                                    });
                                }
                                else
                                {
                                    View toastView = getLayoutInflater().inflate(R.layout.toast, findViewById(R.id.toastLayout));
                                    AppUtility.ShowToast(getApplicationContext(), "Failed to register! User already exists" , toastView,2);
                                }
                            }
                });
    }
}