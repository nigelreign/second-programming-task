package com.nigelreign.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nigelreign.crud.api.Services;
import com.nigelreign.crud.utils.Validations;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        EditText loginEmailAddressText = (EditText) findViewById(R.id.loginEmailAddressText);
        EditText loginPasswordText = (EditText) findViewById(R.id.loginPasswordText);
        String email = loginEmailAddressText.getText().toString().trim();
        String password = loginPasswordText.getText().toString().trim();

        if (TextUtils.isEmpty(loginEmailAddressText.getText()) || TextUtils.isEmpty(loginPasswordText.getText())) {
            Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            if (Validations.emailValidator(email)) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Signing in..."); // Setting Message
                progressDialog.setTitle("Please wait"); // Setting Title
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progressDialog.show(); // Display Progress Dialog
                progressDialog.setCancelable(false);

                Services.login(LoginActivity.this, email, password, progressDialog);
            } else {
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            }

        }
    }
}