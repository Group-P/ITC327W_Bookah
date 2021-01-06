package com.example.itc327w_bookah_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.AppUtilities.AppUtility;
import com.example.itc327w_bookah_mobile.Model.User;
import com.example.itc327w_bookah_mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity implements View.OnClickListener {

    //Variables
    Animation topAnimation;
    ImageView ivLogo;
    TextInputLayout reg_email, reg_firstName, reg_lastName, reg_phoneNumber,reg_id, reg_password,reg_confirmPassword;
    TextInputEditText et_regEmail, et_regFirstName, et_regLastName, et_regPhoneNumber, et_regId, et_regPassword, et_regConfirmPassword;
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        /*et_regPhoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                {
                    if(!AppUtility.getInputText(et_regPhoneNumber).isEmpty())
                    {
                        if(AppUtility.isValidCellphone(AppUtility.getInputText(et_regPhoneNumber)))
                        {
                            table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(AppUtility.getInputText(et_regPhoneNumber)).exists())
                                    {
                                        reg_phoneNumber.setError("Phone Number Exists");
                                        et_regPhoneNumber.setText("");
                                        View toastView = getLayoutInflater().inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toastLayout));
                                        AppUtility.ShowToast(getApplicationContext(), "User already exists!\nPlease login" , toastView,2);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });//execute
                        }
                        else
                        {
                            reg_phoneNumber.setError("Invalid Cellphone Number Format!");
                        }
                    }
                    else
                    {
                        reg_phoneNumber.setError(getString(R.string.phoneNumber_error));
                    }
                }
                else
                {
                    reg_phoneNumber.setError(null);
                }
            }
        });*/


        /*et_regFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    reg_firstName.setError(null);
                }
            }
        });

        et_regLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    reg_lastName.setError(null);
                }
            }
        });*/

        /*et_regId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
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
            }
        });*/

        /*et_regEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
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
            }
        });*/

        /*et_regPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
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
            }
        });*/

        /*et_regConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus)
                {
                    reg_confirmPassword.setError(null);
                }
            }
        });*/

        /*reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(AppUtility.validateInput( new TextInputLayout[] {
                                reg_phoneNumber,reg_firstName,reg_lastName,
                                reg_id,reg_email,reg_password,reg_confirmPassword},
                        getResources().getStringArray(R.array.signUp_errors),
                        et_regPhoneNumber,et_regFirstName,et_regLastName,
                        et_regId,et_regEmail,et_regPassword,et_regConfirmPassword))
                {
                    if(AppUtility.isValidCellphone(AppUtility.getInputText(et_regPhoneNumber)))
                    {
                        if(AppUtility.isValidPassword(AppUtility.getInputText(et_regPassword)))
                        {
                            if(AppUtility.getInputText(et_regPassword).equals(AppUtility.getInputText(et_regConfirmPassword)))
                            {
                                table_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        User user = new User(AppUtility.getInputText(et_regPhoneNumber),
                                                AppUtility.getInputText(et_regFirstName),
                                                AppUtility.getInputText(et_regLastName),
                                                AppUtility.getInputText(et_regId),
                                                AppUtility.getInputText(et_regEmail),
                                                AppUtility.getInputText(et_regPassword));
                                        table_user.child(AppUtility.getInputText(et_regPhoneNumber)).setValue(user);

                                        View toastView = getLayoutInflater().inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toastLayout));
                                        AppUtility.ShowToast(getApplicationContext(), "Registration successful!\nPlease Login..." , toastView,1);
                                        startActivity(new Intent(Register.this,Login.class));
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        View toastView = getLayoutInflater().inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toastLayout));
                                        AppUtility.ShowToast(getApplicationContext(), "Error: " + databaseError.getMessage() , toastView,2);
                                    }
                                });
                            }
                            else
                            {
                                et_regPassword.setText("");
                                et_regConfirmPassword.setText("");
                                reg_password.setError("Password Mismatch!");
                                reg_confirmPassword.setError("Password Mismatch!");
                            }
                        }
                        else
                        {
                            et_regPassword.setText("");
                            et_regConfirmPassword.setText("");
                            reg_password.setError("Password Is Too Simple!");
                        }
                    }
                    else
                    {
                        et_regPhoneNumber.setText("");
                        reg_phoneNumber.setError("Invalid Cellphone Number");
                    }
                }
                else
                {
                    View toastView = getLayoutInflater().inflate(R.layout.toast,(ViewGroup) findViewById(R.id.toastLayout));
                    AppUtility.ShowToast(getApplicationContext(), "Please fill in required information!" , toastView,2);
                }
            }
        });*/

        /*tvAlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
                finish();
            }
        });*/
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
                    public void onComplete(@NonNull Task<AuthResult>task){

                        if (task.isSuccessful()){
                            User user = new User(email,
                                    firstName,
                                    lastName,
                                    idNumber,
                                    phoneNumber,
                                    password);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        //Toast success
                                    }
                                    else {
                                        //Toast fail
                                    }

                                }

                            });
                        }
                        else
                        {
                            Toast.makeText(Register.this,
                                    "Failed to register!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}