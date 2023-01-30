package com.nigelreign.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nigelreign.crud.api.Services;

import org.json.JSONException;
import org.json.JSONObject;

public class AddCustomerActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView usernameText;
    private TextView emailAddressText;
    private TextView streetText;
    private TextView suiteText;
    private TextView cityText;
    private TextView zipcodeText;
    private TextView latText;
    private TextView lngText;
    private TextView phoneText;
    private TextView websiteText;
    private TextView companyNameText;
    private TextView catchPhraseText;
    private TextView bsText;
    ProgressDialog progressDialog;
    String customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        nameText = findViewById(R.id.nameText);
        usernameText = findViewById(R.id.usernameText);
        emailAddressText = findViewById(R.id.emailAddressText);
        streetText = findViewById(R.id.streetText);
        suiteText = findViewById(R.id.suiteText);
        zipcodeText = findViewById(R.id.zipcodeText);
        latText = findViewById(R.id.latText);
        lngText = findViewById(R.id.lngText);
        phoneText = findViewById(R.id.phoneText);
        websiteText = findViewById(R.id.websiteText);
        cityText = findViewById(R.id.cityText);
        companyNameText = findViewById(R.id.companyNameText);
        catchPhraseText = findViewById(R.id.catchPhraseText);
        bsText = findViewById(R.id.bsText);
    }

    public void addCustomer(View view) {
        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            JSONObject geoData = new JSONObject();
            geoData.put("lat", latText.getText().toString());
            geoData.put("lng", lngText.getText().toString());

            JSONObject companyData = new JSONObject();
            companyData.put("name", companyNameText.getText().toString());
            companyData.put("catchPhrase", catchPhraseText.getText().toString());
            companyData.put("bs", bsText.getText().toString());

            JSONObject addressData = new JSONObject();
            addressData.put("street", streetText.getText().toString());
            addressData.put("suite", suiteText.getText().toString());
            addressData.put("city", cityText.getText().toString());
            addressData.put("zipcode", zipcodeText.getText().toString());
            addressData.put("geo", geoData);

            jsonObj_.put("name", nameText.getText().toString());
            jsonObj_.put("username", usernameText.getText().toString());
            jsonObj_.put("email", emailAddressText.getText().toString());
            jsonObj_.put("address", addressData);
            jsonObj_.put("phone", phoneText.getText().toString());
            jsonObj_.put("website", websiteText.getText().toString());
            jsonObj_.put("company", companyData);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog = new ProgressDialog(AddCustomerActivity.this);
        progressDialog.setMessage("Updating details..."); // Setting Message
        progressDialog.setTitle("Please wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        Services.addCustomer(AddCustomerActivity.this, gsonObject, progressDialog);

    }
}