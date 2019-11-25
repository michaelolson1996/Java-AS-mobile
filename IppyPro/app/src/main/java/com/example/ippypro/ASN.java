package com.example.ippypro;

public class ASN {
    private String asnName;
    private String asnDomain;
    private String asnOrganization;
    private String asnCode;
    private String asnType;

    public ASN(String asnName, String asnDomain, String asnOrganization, String asnCode, String asnType) {
        this.asnName = asnName;
        this.asnDomain = asnDomain;
        this.asnOrganization = asnOrganization;
        this.asnCode = asnCode;
        this.asnType = asnType;
    }

    public String getString() {
        String asnStr = "\nASN Name: " + asnName + "\nDomain Name: " + asnDomain + "\nOrganization: "
                + asnOrganization + "\nASN Code: " + asnCode + "\nType: " + asnType + "\n";
        return asnStr;
    }
}
