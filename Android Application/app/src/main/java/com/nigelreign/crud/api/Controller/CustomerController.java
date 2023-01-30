package com.nigelreign.crud.api.Controller;

import com.google.gson.JsonObject;
import com.nigelreign.crud.api.models.CustomerResults;
import com.nigelreign.crud.api.models.LoginResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CustomerController {
    @GET("api/v1/customer/")
    Call<CustomerResults.Results> getCustomers(@Header("Authorization") String accessToken);

    @GET("https://jsonplaceholder.typicode.com/users")
    Call<List<CustomerResults.Customer>> getUsers();

    @HTTP(method = "DELETE", path = "api/v1/customer/{customerId}/", hasBody = true)
    Call<Void> deleteCustomer(@Header("Authorization") String accessToken, @Path("customerId") int customerId);

    @Headers({
            "Accept: application/json",
    })
    @POST("/api/v1/customerList/")
    Call<CustomerResults.Customer> addCustomerList(@Body JsonObject payload);

    @Headers({
            "Accept: application/json",
    })
    @PATCH("api/v1/customer/{customerId}/")
    Call<CustomerResults.Customer> updateCustomer(@Header("Authorization") String accessToken, @Body JsonObject payload, @Path("customerId") String customerId);

    @Headers({
            "Accept: application/json",
    })
    @POST("api/v1/customer/")
    Call<CustomerResults.Customer> addCustomer(@Header("Authorization") String accessToken, @Body JsonObject gsonObject);
}
