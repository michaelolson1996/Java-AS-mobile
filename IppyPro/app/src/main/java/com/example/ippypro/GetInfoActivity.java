package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class GetInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);
        Bundle adresses = getIntent().getExtras();
        utilizeAddresses(adresses);
    }

    public void utilizeAddresses(Bundle addresses) {
        ArrayList<String> ipArr = addresses.getStringArrayList("addresses");
        getIPAddressInfo(ipArr.get(0));
    }

    public void getIPAddressInfo(String address) {

    }
}
