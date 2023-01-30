package com.nigelreign.crud;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.nigelreign.crud.api.ApiClient;
import com.nigelreign.crud.api.Controller.CustomerController;
import com.nigelreign.crud.adapters.CustomerAdapter;
import com.nigelreign.crud.api.Services;
import com.nigelreign.crud.api.models.CustomerResults;
import com.nigelreign.crud.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static TextView noCustomer;
    public static ListView lView;
    public static List<CustomerResults.Customer> customerList = new ArrayList<>();
    public static List<JsonObject> payloadList = new ArrayList<JsonObject>();
    ;
    ProgressDialog progressDialog;
    public static ProgressBar psBar;
    public static CustomerAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noCustomer = (TextView) findViewById(R.id.noCustomer);
        psBar = (ProgressBar) findViewById(R.id.customerLoader);
        psBar.setVisibility(VISIBLE);

        lView = (ListView) findViewById(R.id.customerListView);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String name = customerList.get(position).getName();
                String email = customerList.get(position).getEmail();
                String username = customerList.get(position).getUsername();
                String phone = customerList.get(position).getPhone();
                String website = customerList.get(position).getWebsite();
                String street = customerList.get(position).getAddress().getStreet();
                String suite = customerList.get(position).getAddress().getSuite();
                String city = customerList.get(position).getAddress().getCity();
                String zipcode = customerList.get(position).getAddress().getZipcode();
                String lat = customerList.get(position).getAddress().getGeo().getLat();
                String lng = customerList.get(position).getAddress().getGeo().getLng();
                String companyName = customerList.get(position).getCompany().getName();
                String catchPhrase = customerList.get(position).getCompany().getCatchPhrase();
                String bs = customerList.get(position).getCompany().getBs();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("street", street);
                editor.putString("suite", suite);
                editor.putString("city", city);
                editor.putString("zipcode", zipcode);
                editor.putString("lat", lat);
                editor.putString("lng", lng);
                editor.putString("phone", phone);
                editor.putString("website", website);
                editor.putString("companyName", companyName);
                editor.putString("catchPhrase", catchPhrase);
                editor.putString("bs", bs);

                //redirect to the edit page
            }
        });

        getCustomers(MainActivity.this);
    }

    public static void getCustomers(Context context) {
        CustomerController customerController = ApiClient.get_retrofit_instance().create(CustomerController.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String accessToken = preferences.getString("accessToken", "");

        Call<CustomerResults.Results> call = customerController.getCustomers("Bearer " + accessToken);
        call.enqueue(new Callback<CustomerResults.Results>() {
            @Override
            public void onResponse(Call<CustomerResults.Results> call, Response<CustomerResults.Results> response) {
                customerList.clear();
                if (response.code() == 200) {
                    ArrayList<CustomerResults.Customer> customerResults = response.body().getResults();
//                    Log.e("TAG ==>", String.valueOf(customerResults.size()));

                    psBar.setVisibility(GONE);
                    if (customerResults.size() > 0) {
                        noCustomer.setVisibility(GONE);

                        for (CustomerResults.Customer customerResult : customerResults) {
//                            Log.e("TAG list ==>", String.valueOf(customerResult.getEmail()));
                            customerList.add(customerResult);
                        }
                        lAdapter = new CustomerAdapter(context, customerList);

                        lView.setAdapter(lAdapter);
                    } else {
                        customerList.clear();
                        noCustomer.setVisibility(VISIBLE);
                    }
                } else {
                    psBar.setVisibility(GONE);
                    noCustomer.setVisibility(VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<CustomerResults.Results> call, Throwable t) {
                Log.e("TAG ==> ", String.valueOf(t));
                customerList.clear();
                psBar.setVisibility(GONE);
                noCustomer.setVisibility(VISIBLE);
                Toast.makeText(context, "Failed to retrieve data", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getUsers(View view) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Populating database..."); // Setting Message
        progressDialog.setTitle("Please wait"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        CustomerController customerController = ApiClient.get_retrofit_instance().create(CustomerController.class);

        Call<List<CustomerResults.Customer>> call = customerController.getUsers();
        call.enqueue(new Callback<List<CustomerResults.Customer>>() {
            @Override
            public void onResponse(Call<List<CustomerResults.Customer>> call, Response<List<CustomerResults.Customer>> response) {
                if (response.code() == 200) {
                    ArrayList<CustomerResults.Customer> customerResults = (ArrayList<CustomerResults.Customer>) response.body();

                    if (customerResults.size() > 0) {
                        JsonObject payload = null;
                        for (CustomerResults.Customer customerResult : customerResults) {
                            payload = Utils.getGsonPayload(customerResult);
                            payloadList.add(payload);
                        }
//                        Log.e("TAG ==> ", String.valueOf(payloadList));
                        Services.addCustomerList(MainActivity.this, payloadList, progressDialog);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CustomerResults.Customer>> call, Throwable t) {
                psBar.setVisibility(GONE);
                Toast.makeText(MainActivity.this, "Failed to retrieve data", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addUsers(View view){
        Intent intent = new Intent(this, AddCustomerActivity.class);
        this.startActivity(intent);
    }
    public void logout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
    }
}


