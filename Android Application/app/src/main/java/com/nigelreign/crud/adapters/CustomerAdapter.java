package com.nigelreign.crud.adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nigelreign.crud.EditActivity;
import com.nigelreign.crud.MainActivity;
import com.nigelreign.crud.R;
import com.nigelreign.crud.api.ApiClient;
import com.nigelreign.crud.api.Controller.CustomerController;
import com.nigelreign.crud.api.models.CustomerResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerAdapter extends BaseAdapter {

    Context context;
    private final List<CustomerResults.Customer> values;
    ProgressDialog progressDialog;
    static AlertDialog.Builder builder;

    public CustomerAdapter(Context context, List<CustomerResults.Customer> values) {
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.users_list, parent, false);
            viewHolder.customerName = (TextView) convertView.findViewById(R.id.customerName);
            viewHolder.customerEmailAddress = (TextView) convertView.findViewById(R.id.customerEmailAddress);
            viewHolder.customerPhoneNumber = (TextView) convertView.findViewById(R.id.customerPhoneNumber);

            ImageView btnDeleteCustomer = (ImageView) convertView.findViewById(R.id.deleteCustomer);
            ImageView btnEditCustomer = (ImageView) convertView.findViewById(R.id.editCustomer);

            btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {

                        builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete")
                                .setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        progressDialog = new ProgressDialog(context);
                                        progressDialog.setMessage("Deleting customer"); // Setting Message
                                        progressDialog.setTitle("Please wait"); // Setting Title
                                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                                        progressDialog.show(); // Display Progress Dialog
                                        progressDialog.setCancelable(false);

                                        CustomerController customerController = ApiClient.get_retrofit_instance().create(CustomerController.class);
                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                                        String accessToken = preferences.getString("accessToken", "");

                                        int customerId = values.get(position).getId();

                                        Call<Void> call = customerController.deleteCustomer("Bearer " + accessToken, customerId);
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                progressDialog.dismiss();
                                                if (response.code() == 204) {
                                                    Toast.makeText(context, "Deleted successfully", Toast.LENGTH_LONG).show();
                                                    MainActivity.getCustomers(context);
                                                } else {
                                                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                progressDialog.dismiss();
//                                                Log.e("TAG ==> ", t.toString());
                                                Toast.makeText(context, "Failed to delete", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("Delete Customer");
                        alert.show();
                    } catch (Exception e) {
                        Log.e("=======", e.getMessage(), e);
                    }
                }

            });

            btnEditCustomer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {

                        int id = values.get(position).getId();
                        String name = values.get(position).getName();
                        String email = values.get(position).getEmail();
                        String username = values.get(position).getUsername();
                        String phone = values.get(position).getPhone();
                        String website = values.get(position).getWebsite();
                        String street = values.get(position).getAddress().getStreet();
                        String suite = values.get(position).getAddress().getSuite();
                        String city = values.get(position).getAddress().getCity();
                        String zipcode = values.get(position).getAddress().getZipcode();
                        String lat = values.get(position).getAddress().getGeo().getLat();
                        String lng = values.get(position).getAddress().getGeo().getLng();
                        String companyName = values.get(position).getCompany().getName();
                        String catchPhrase = values.get(position).getCompany().getCatchPhrase();
                        String bs = values.get(position).getCompany().getBs();

                        Intent intent = new Intent(context, EditActivity.class);
                        intent.putExtra("name", name);
                        intent.putExtra("username", username);
                        intent.putExtra("email", email);
                        intent.putExtra("street", street);
                        intent.putExtra("suite", suite);
                        intent.putExtra("city", city);
                        intent.putExtra("zipcode", zipcode);
                        intent.putExtra("lat", lat);
                        intent.putExtra("lng", lng);
                        intent.putExtra("phone", phone);
                        intent.putExtra("website", website);
                        intent.putExtra("companyName", companyName);
                        intent.putExtra("catchPhrase", catchPhrase);
                        intent.putExtra("bs", bs);
                        intent.putExtra("id", String.valueOf(id));
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }
                }

            });
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.customerName.setText(values.get(position).getName());
        viewHolder.customerEmailAddress.setText(values.get(position).getEmail());
        viewHolder.customerPhoneNumber.setText(values.get(position).getPhone());

        return convertView;
    }

    private static class ViewHolder {
        TextView customerName;
        TextView customerEmailAddress;
        TextView customerPhoneNumber;
//        ImageView productImage;
    }
}
