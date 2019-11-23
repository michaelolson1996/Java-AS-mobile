package com.example.ippypro;

public class Location {
    private String country;
    private String capital;
    private String iso;
    private String city;
    private Integer zip;
    private double longitude;
    private double latitude;

    public Location(String country, String capital, String iso, String city, Integer zip, double longitude, double latitude) {
        if (zip.equals(null))
            zip = 0;
        this.country = country;
        this.capital = capital;
        this.iso = iso;
        this.city = city;
        this.zip = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getString() {
        String locationStr = "\nGeographics\n" + "Country: " + country + "\nCapital: " + capital + "\nISO: " + iso
                + "\nCity: " + city + "\nZip: " + zip + "\nLong: " + longitude + "\nLatitude: " + latitude;
        return locationStr;
    }
}
