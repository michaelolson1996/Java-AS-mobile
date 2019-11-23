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
}
