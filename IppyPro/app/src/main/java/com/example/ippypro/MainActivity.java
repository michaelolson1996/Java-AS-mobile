package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Bundle ip = getIntent().getExtras();
            String ipStr = ip.getString("ip");
            EditText tv = findViewById(R.id.getIP);
            tv.setText(ipStr);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        Button setipbtn = findViewById(R.id.setipbtn);
        Button aboutbtn = findViewById(R.id.aboutbtn);
        Button storagebtn = findViewById(R.id.storagebtn);
        setipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testIPAddress();
            }
        });
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goAbout();
            }
        });
        storagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goStorage();
            }
        });
    }
    public void testIPAddress() {
        String ipAddress = getIPAddress();
        if (ipAddress.length() >= 7) {
            try {
                ArrayList<String> ipArr = ipStrToArr(ipAddress);
                ArrayList<String> ipGroupNums = ipGroupNums(ipArr);
                if (checkIPComponents(ipGroupNums)) {
                    String convertedBinaryIP = convertIPAddress(ipGroupNums);
                    String convertedHexIP = convertBinaryToHex(convertedBinaryIP);
                    String convertedOctalIP = convertBinaryToOctal(convertedBinaryIP);
                    packageAddresses(ipAddress, convertedBinaryIP, convertedHexIP, convertedOctalIP);
                    clearIPAddress();
                } else {
                    errMessage();
                }
            } catch(Exception e) {
                errMessage();
            }
        } else {
            errMessage();
        }
    }
    public String getIPAddress() {
        EditText ip = findViewById(R.id.getIP);
        String ipAddress = ip.getText().toString();
        return ipAddress;
    }
    public void clearIPAddress() {
        EditText ip = findViewById(R.id.getIP);
        ip.setText("");
    }
    public void errMessage() {
        TextView err = findViewById(R.id.errmessage);
        String errMessage = "Please check to make sure your address is formatted properly";
        err.setText(errMessage);
    }
    public ArrayList<String> ipStrToArr(String ipAddress) {
        ArrayList<String> ipArr = new ArrayList<>();
        for (int i = 0; i < ipAddress.length(); i++) {
            ipArr.add(ipAddress.substring(i, i + 1));
        }
        return ipArr;
    }
    public ArrayList<String> ipGroupNums(ArrayList<String> ipArr) {
        ArrayList<String> ipGroupNums = new ArrayList<>();
        String ipNum = "";
        for (int i = 0; i < ipArr.size(); i++) {
            if (!ipArr.get(i).matches("[.]")) {
                ipNum += ipArr.get(i);
            } else {
                ipGroupNums.add(ipNum);
                ipNum = "";
            }
        }
        if (ipNum.length() != 0) {
            ipGroupNums.add(ipNum);
        }
        return ipGroupNums;
    }
    public boolean checkIPComponents(ArrayList<String> components) {
        try {
            for (int i = 0; i < components.size(); i++) {
                int ipComponent = Integer.parseInt(components.get(i));
                if (ipComponent <= 255 && ipComponent >= 0) {
                    continue;
                } else {
                    return false;
                }
            }
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    public String convertIPAddress(ArrayList<String> components) {
        String binaryVal = "";
        for (int i = 0; i < components.size(); i++) {
            String binaryComponent = convertIPToBinary(components.get(i));
            binaryVal += binaryComponent;
        }
        return binaryVal;
    }
    public String convertIPToBinary(String component) {
        int componentInt = Integer.parseInt(component);
        int initValue = 128;
        String binaryComponent = "";

        for (int i = 0; i < 8; i++) {
            if (componentInt - initValue >= 0) {
                componentInt = componentInt - initValue;
                binaryComponent += 1;
                initValue = initValue / 2;
            } else {
                binaryComponent += 0;
                initValue = initValue / 2;
            }
        }
        return binaryComponent;
    }
    public String convertBinaryToHex(String binaryStr) {
        int counter = 0;
        StringBuilder hexStr = new StringBuilder();
        int j;
        int hexDigit;
        for (int i = 0; i < 8; i++) {
            hexDigit = 0;
            for (j = 0; j < 4; j++) {
                 if (binaryStr.substring(counter, (counter + 1)).equals("1")) {
                     if (j == 0) {
                         hexDigit = hexDigit + 8;
                     } else if (j == 1) {
                         hexDigit = hexDigit + 4;
                     } else if (j == 2) {
                         hexDigit = hexDigit + 2;
                     } else {
                         hexDigit = hexDigit + 1;
                     }
                     counter = counter + 1;
                 } else {
                     counter = counter + 1;
                     continue;
                 }
            }

            switch(hexDigit) {
                case 10:
                    hexStr.append("A");
                    break;
                case 11:
                    hexStr.append("B");
                    break;
                case 12:
                    hexStr.append("C");
                    break;
                case 13:
                    hexStr.append("D");
                    break;
                case 14:
                    hexStr.append("E");
                    break;
                case 15:
                    hexStr.append("F");
                    break;
                default:
                    hexStr.append(hexDigit);
            }
        }
        return hexStr.toString();
    }
    public String convertBinaryToOctal(String binaryStr) {
        StringBuilder octStr = new StringBuilder();
        binaryStr = 0 + binaryStr;
        int octDigit;
        int counter = 0;

        for (int i = 0; i < 11; i++) {
            octDigit = 0;
            for (int j = 0; j < 3; j++) {
                if (binaryStr.substring(counter, (counter + 1)).equals("1")) {
                    if (j == 0) {
                        octDigit = octDigit + 4;
                    } else if (j == 1) {
                        octDigit = octDigit + 2;
                    } else {
                        octDigit = octDigit + 1;
                    }
                    counter = counter + 1;
                } else {
                    counter = counter + 1;
                    continue;
                }
            }
            octStr.append(octDigit);
        }
        return octStr.toString();
    }
    public void packageAddresses(String ipAddress, String binaryValue, String hexValue, String octalValue) {
        ArrayList<String> ipPackage = new ArrayList<>();
        ipPackage.add(ipAddress);
        ipPackage.add(binaryValue);
        ipPackage.add(hexValue);
        ipPackage.add(octalValue);
        sendIPAddress(ipPackage);
    }
    public void sendIPAddress(ArrayList<String> ipPackage) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, GetInfoActivity.class);
        bundle.putStringArrayList("addresses", ipPackage);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void goStorage() {
//        Intent intent = new Intent(this, SaveActivity.class);
//        startActivity(intent);
        TextView err = findViewById(R.id.errmessage);
        err.setText("This feature will be available in the near future :)");
    }
    public void goAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}