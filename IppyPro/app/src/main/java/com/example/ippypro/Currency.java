package com.example.ippypro;

public class Currency {
    private String nativeName;
    private String code;
    private String name;
    private String symbol;

    public Currency(String nativeName, String code, String name, String symbol) {
        this.nativeName = nativeName;
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }
}
