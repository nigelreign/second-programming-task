package com.nigelreign.crud.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nigelreign.crud.EditActivity;
import com.nigelreign.crud.MainActivity;
import com.nigelreign.crud.api.Controller.CustomerController;
import com.nigelreign.crud.api.Controller.UserAuthController;
import com.nigelreign.crud.api.models.CustomerResults;
import com.nigelreign.crud.api.models.LoginResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Services {
    static UserAuthController userAuthController = ApiClient.get_retrofit_instance().create(UserAuthController.class);
    static CustomerController customerController = ApiClient.get_retrofit_instance().create(CustomerController.class);


    public static void login(Context context, String email, String password, ProgressDialog progressDialog) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            JSONObject childData = new JSONObject();

            jsonObj_.put("email", email);
            jsonObj_.put("password", password);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<LoginResults> call = userAuthController.login(gsonObject);
        call.enqueue(new Callback<LoginResults>() {
            @Override
            public void onResponse(Call<LoginResults> call, Response<LoginResults> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Nigel welcomes you.", Toast.LENGTH_LONG).show();

                    String accessToken = response.body().getAccessToken();
//                    Log.e("TAG Token ==> ", accessToken);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("accessToken", accessToken);
                    editor.apply();

                    progressDialog.dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Unable to log in with provided credentials.", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResults> call, Throwable t) {
                Log.e("TAG ==> ", t.toString());
                Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    public static void addCustomerList(Context context, List<JsonObject> payload, ProgressDialog progressDialog) {
        JsonObject gsonObject = new JsonObject();
        try {

            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("customers", payload);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<CustomerResults.Customer> call = customerController.addCustomerList(gsonObject);
        call.enqueue(new Callback<CustomerResults.Customer>() {
            @Override
            public void onResponse(Call<CustomerResults.Customer> call, Response<CustomerResults.Customer> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Toast.makeText(context, "Record added successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Failed to populate record", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResults.Customer> call, Throwable t) {
                Log.e("TAG ==> ", t.toString());
                Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    public static void updateCustomer(Context context, JsonObject gsonObject, ProgressDialog progressDialog, String customerId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = preferences.getString("accessToken", "");

        Call<CustomerResults.Customer> call = customerController.updateCustomer("Bearer " + accessToken, gsonObject, customerId);
        call.enqueue(new Callback<CustomerResults.Customer>() {
            @Override
            public void onResponse(Call<CustomerResults.Customer> call, Response<CustomerResults.Customer> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Toast.makeText(context, "Customer details updated successfully", Toast.LENGTH_LONG).show();
                    MainActivity.getCustomers(context);
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, "Failed to update details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResults.Customer> call, Throwable t) {
                Log.e("TAG ==> ", t.toString());
                Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    public static void addCustomer(Context context, JsonObject gsonObject, ProgressDialog progressDialog) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = preferences.getString("accessToken", "");

        Call<CustomerResults.Customer> call = customerController.addCustomer("Bearer " + accessToken, gsonObject);
        call.enqueue(new Callback<CustomerResults.Customer>() {
            @Override
            public void onResponse(Call<CustomerResults.Customer> call, Response<CustomerResults.Customer> response) {
                progressDialog.dismiss();
                if (response.code() == 201) {
                    Toast.makeText(context, "Customer details added successfully", Toast.LENGTH_LONG).show();
                    MainActivity.getCustomers(context);
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);

                } else {
                    Toast.makeText(context, "Failed to add details", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResults.Customer> call, Throwable t) {
                Log.e("TAG ==> ", t.toString());
                Toast.makeText(context, "Something went wrong. Please try again", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}

