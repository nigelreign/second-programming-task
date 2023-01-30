package com.nigelreign.crud.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CustomerResults {
    public class Customer {
        @SerializedName("id")
        @Expose
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //        ==========================================
        @SerializedName("username")
        @Expose
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        //        ==========================================
        @SerializedName("email")
        @Expose
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        //        ==========================================
        @SerializedName("phone")
        @Expose
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        //        ==========================================
        @SerializedName("website")
        @Expose
        private String website;

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        @SerializedName("address")
        private Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }


        @SerializedName("company")
        private Company company;

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

    }


    public class Address {
        @SerializedName("street")
        @Expose
        private String street;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        //========================================
        @SerializedName("suite")
        @Expose
        private String suite;

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        //========================================
        @SerializedName("city")
        @Expose
        private String city;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        //========================================
        @SerializedName("phone")
        @Expose
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        //========================================
        @SerializedName("website")
        @Expose
        private String website;

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        //========================================
        @SerializedName("zipcode")
        @Expose
        private String zipcode;

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        @SerializedName("geo")
        private Geo geo;

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

    }

    public class Geo {
        @SerializedName("lat")
        @Expose
        private String lat;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        //========================================
        @SerializedName("lng")
        @Expose
        private String lng;

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }

    public class Company {
        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //========================================
        @SerializedName("catchPhrase")
        @Expose
        private String catchPhrase;

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        //========================================
        @SerializedName("bs")
        @Expose
        private String bs;

        public String getBs() {
            return bs;
        }

        public void setBs(String bs) {
            this.bs = bs;
        }
    }

    public class Results {
        private float count;
        private String next = null;
        private String previous = null;
        ArrayList<Customer> results = new ArrayList<Customer>();


        // Getter Methods

        public float getCount() {
            return count;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

        public ArrayList<Customer> getResults() {
            return results;
        }

        // Setter Methods

        public void setCount(float count) {
            this.count = count;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public void setResults(ArrayList<Customer> results) {
            this.results = results;
        }
    }
}

