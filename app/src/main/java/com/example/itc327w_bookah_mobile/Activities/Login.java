package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itc327w_bookah_mobile.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    //Variables
    Animation topAnimation;
    ImageView logo_image;
    TextInputLayout login_phoneNumber, login_password;
    TextInputEditText et_loginPhoneNUmber, et_loginPassword;
    TextView tvRegisterAccount;
    CheckBox cb_StayLoggedIn;
    Button login_btn, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        logo_image = findViewById(R.id.login_logo);

        login_phoneNumber = findViewById(R.id.login_phoneNumber);
        et_loginPhoneNUmber = findViewById(R.id.et_loginPhoneNumber);

        login_password = findViewById(R.id.login_password);
        et_loginPassword = findViewById(R.id.et_loginPassword);

        cb_StayLoggedIn = findViewById(R.id.cb_StayLoggedIn);
        login_btn = findViewById(R.id.btnLogin);
        tvRegisterAccount = findViewById(R.id.tvRegisterAccount);

        //Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        logo_image.startAnimation(topAnimation);

        //Functionality

        /*Paper.init(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.isConnectedToInternet(getBaseContext())) {
                    if (cb_StaySignedIn.isChecked()) {
                        Paper.book().write(Common.USER_KEY, AppUtility.getInputText(et_loginCellphone));
                        Paper.book().write(Common.PWD_KEY, AppUtility.getInputText(et_loginPassword));
                    }

                    if (AppUtility.validateInput(new TextInputLayout[]{
                                    login_cellphone, login_password
                            }, getResources().getStringArray(R.array.signIn_errors),
                            et_loginCellphone, et_loginPassword)) {
                        table_user.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(AppUtility.getInputText(et_loginCellphone)).exists()) {
                                    User user = dataSnapshot.child(AppUtility.getInputText(et_loginCellphone)).getValue(User.class);
                                    if (user != null) {
                                        user.setCellphone(AppUtility.getInputText(et_loginCellphone));
                                        if (user.getPassword().equals(AppUtility.getInputText(et_loginPassword))) {
                                            View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
                                            AppUtility.ShowToast(getApplicationContext(), "Login successful", toastView, 1);
                                            Intent helloSplashIntent = new Intent(SignIn.this, HelloSplash.class);
                                            Common.currentUser = user;
                                            startActivity(helloSplashIntent);
                                            finish();
                                        }//end password if
                                        else {
                                            login_password.setError("Incorrect Password");
                                            et_loginPassword.setText("");
                                        }
                                    }//end user null if

                                }//end exist if
                                else {
                                    login_cellphone.setError("Number Does Not Exist");
                                    et_loginCellphone.setText("");
                                    et_loginPassword.setText("");
                                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
                                    AppUtility.ShowToast(getApplicationContext(), "Cellphone number does not exist!\nPlease register", toastView, 1);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
                                AppUtility.ShowToast(getApplicationContext(), "Error: " + databaseError.getMessage(), toastView, 2);
                            }
                        });
                    }
                }
                else
                {
                    View toastView = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
                    AppUtility.ShowToast(getApplicationContext(), "Unable to connect!\nPlease check your internet connection", toastView, 2);
                }
            }
        });

        tvRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this,SignUp.class));
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resetIntent = new Intent(SignIn.this,PasswordReset.class);
                startActivity(resetIntent);
            }
        });*/

        tvRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
                finish();
            }
        });

    }
}