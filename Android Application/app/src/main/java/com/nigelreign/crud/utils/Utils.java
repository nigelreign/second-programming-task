package com.nigelreign.crud.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nigelreign.crud.api.models.CustomerResults;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static JsonObject getGsonPayload(CustomerResults.Customer customerResult) {

        JsonObject payload = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            JSONObject geoData = new JSONObject();
            geoData.put("lat", customerResult.getAddress().getGeo().getLat());
            geoData.put("lng", customerResult.getAddress().getGeo().getLng());

            JSONObject companyData = new JSONObject();
            companyData.put("name", customerResult.getCompany().getName());
            companyData.put("catchPhrase", customerResult.getCompany().getCatchPhrase());
            companyData.put("bs", customerResult.getCompany().getBs());

            JSONObject addressData = new JSONObject();
            addressData.put("street", customerResult.getAddress().getStreet());
            addressData.put("suite", customerResult.getAddress().getSuite());
            addressData.put("city", customerResult.getAddress().getCity());
            addressData.put("zipcode", customerResult.getAddress().getZipcode());
            addressData.put("geo", geoData);

            jsonObj_.put("name", customerResult.getName());
            jsonObj_.put("username", customerResult.getUsername());
            jsonObj_.put("email", customerResult.getEmail());
            jsonObj_.put("address", addressData);
            jsonObj_.put("phone", customerResult.getPhone());
            jsonObj_.put("website", customerResult.getWebsite());
            jsonObj_.put("company", companyData);

            JsonParser jsonParser = new JsonParser();
            payload = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return payload;
    }

}
