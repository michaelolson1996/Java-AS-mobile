package com.example.ippypro;

public class Security {
    private boolean isCrawler;
    private boolean isProxy;
    private boolean isTor;

    public Security(boolean isCrawler, boolean isProxy, boolean isTor) {
        this.isCrawler = isCrawler;
        this.isProxy = isProxy;
        this.isTor = isTor;
    }

    public String getString() {
        String securityStr = "\nSecurity\nIs a Crawler: " + isCrawler + "\nIs a Proxy: " + isProxy + "\nIs a Tor: " + isTor;
        return securityStr;
    }
}
