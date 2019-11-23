package com.example.ippypro;

public class IPInfo {
    private String ip;
    private String ipBinary;
    private String ipHex;
    private String ipOct;
    private String requesterIP;
    private String execTime;

    public IPInfo(String ip, String ipBinary, String ipHex, String ipOct) {
        this.ip = ip;
        this.ipBinary = ipBinary;
        this.ipHex = ipHex;
        this.ipOct = ipOct;
    }

    public void setIPAndTime(String requesterIP, String execTime) {
        this.requesterIP = requesterIP;
        this.execTime = execTime;
    }
}
