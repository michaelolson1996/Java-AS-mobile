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
        return null;
    }

    public String displayGeneral() {
        return null;
    }

    public String displayCurrency() {
        return null;
    }

    public String displayLocation() {
        return null;
    }

    public String displaySecurity() {
        return null;
    }

    public String displayASN() {
        return null;
    }

    public String displayTimezone() {
        return null;
    }
}
