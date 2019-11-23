package com.example.ippypro;

public class IPContainer {
    IPInfo IPInfo;
    Currency Currency;
    Location Location;
    Security Security;
    ASN ASN;
    Timezone Timezone;

    public IPContainer(IPInfo ipinfo, Currency currency, Location location, Security security, ASN asn, Timezone timezone) {
        this.IPInfo = ipinfo;
        this.Currency = currency;
        this.Location = location;
        this.Security = security;
        this.ASN = asn;
        this.Timezone = timezone;
    }

    public String displayAll() {
        String displayStr = IPInfo.getString();
        String currencyStr = Currency.getString();
        String locationStr = Location.getString();
        String timezoneStr = Timezone.getString();
        String asnStr = ASN.getString();
        String securityStr = Security.getString();
        return displayStr + currencyStr + locationStr + timezoneStr + asnStr + securityStr;
    }

    public String displayGeneral() {
        String displayStr = IPInfo.getString();
        String currencyStr = Currency.getString();
        String timezoneStr = Timezone.getString();
        return displayStr + currencyStr + timezoneStr;
    }

    public String displayLocation() {
        String displayStr = IPInfo.getString();
        String locationStr = Location.getString();
        return displayStr + locationStr;
    }

    public String displayASN() {
        String displayStr = IPInfo.getString();
        String asnStr = ASN.getString();
        String securityStr = Security.getString();
        return displayStr + asnStr + securityStr;
    }
}
