package com.nigelreign.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nigelreign.crud.api.Services;

import org.json.JSONException;
import org.json.JSONObject;

public class EditActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_edit);

        String name = getIntent().getStringExtra("name");
        nameText = findViewById(R.id.nameText);
        nameText.setText(name);
        String username = getIntent().getStringExtra("username");
        usernameText = findViewById(R.id.usernameText);
        usernameText.setText(username);
        String email = getIntent().getStringExtra("email");
        emailAddressText = findViewById(R.id.emailAddressText);
        emailAddressText.setText(email);
        String street = getIntent().getStringExtra("street");
        streetText = findViewById(R.id.streetText);
        streetText.setText(street);
        String suite = getIntent().getStringExtra("suite");
        suiteText = findViewById(R.id.suiteText);
        suiteText.setText(suite);
        String zipcode = getIntent().getStringExtra("zipcode");
        zipcodeText = findViewById(R.id.zipcodeText);
        zipcodeText.setText(zipcode);
        String lat = getIntent().getStringExtra("lat");
        latText = findViewById(R.id.latText);
        latText.setText(lat);
        String lng = getIntent().getStringExtra("lng");
        lngText = findViewById(R.id.lngText);
        lngText.setText(lng);
        String phone = getIntent().getStringExtra("phone");
        phoneText = findViewById(R.id.phoneText);
        phoneText.setText(phone);
        String website = getIntent().getStringExtra("website");
        websiteText = findViewById(R.id.websiteText);
        websiteText.setText(website);
        String city = getIntent().getStringExtra("city");
        cityText = findViewById(R.id.cityText);
        cityText.setText(city);
        String companyName = getIntent().getStringExtra("companyName");
        companyNameText = findViewById(R.id.companyNameText);
        companyNameText.setText(companyName);
        String catchPhrase = getIntent().getStringExtra("catchPhrase");
        catchPhraseText = findViewById(R.id.catchPhraseText);
        catchPhraseText.setText(catchPhrase);
        bsText = findViewById(R.id.bsText);
        String bs = getIntent().getStringExtra("bs");
        bsText.setText(bs);

        customerId = getIntent().getStringExtra("id");
    }

    public void update(View view) {
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

        progressDialog = new ProgressDialog(EditActivity.this);
        progressDialog.setMessage("Updating details..."); // Setting Message
        progressDialog.setTitle("Please wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        Services.updateCustomer(EditActivity.this, gsonObject, progressDialog, getIntent().getStringExtra("id"));

    }
}