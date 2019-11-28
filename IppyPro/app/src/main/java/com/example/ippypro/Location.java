package com.example.ippypro;

public class Location implements java.io.Serializable {
    private String country;
    private String capital;
    private String iso;
    private String city;
    private double longitude;
    private double latitude;

    public Location(String country, String capital, String iso, String city, double longitude, double latitude) {
        this.country = country;
        this.capital = capital;
        this.iso = iso;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getString() {
        String locationStr = "\nGeographics\n" + "Country: " + country + "\nCapital: " + capital + "\nISO: " + iso
                + "\nCity: " + city + "\nLong: " + longitude + "\nLatitude: " + latitude;
        return locationStr;
    }
}
