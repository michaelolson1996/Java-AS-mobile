package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GetInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_info);
        Bundle addresses = getIntent().getExtras();
        utilizeAddresses(addresses);
    }

    public void utilizeAddresses(Bundle addresses) {
        ArrayList<String> ipArr = addresses.getStringArrayList("addresses");
        SendfeedbackJob job = new SendfeedbackJob();
        job.execute(ipArr.get(0));
    }

    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            HttpURLConnection httpClient;
            try {
                Secret secret = new Secret();
                String configuredURL = "https://api.smartip.io/" + params[0] + "?api_key=" + secret.getKey();
                URL url = new URL(configuredURL);
                httpClient = (HttpURLConnection) url.openConnection();
                httpClient.setRequestMethod("GET");
                int status = httpClient.getResponseCode();

                if (status != 200) {
                    return "Unable to retrieve IP Address";
                } else {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(httpClient.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        builder.append(line + "\n");
                    }
                    reader.close();
                    httpClient.disconnect();
                    return builder.toString();
                }
            } catch(Exception e) {
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String message) {
            TextView tv = findViewById(R.id.display);
            try {
                JSONObject jsonAddress = new JSONObject(message);
//                DISPLAY INFORMATION
                String requesterIP = jsonAddress.getString("requester-ip");
                String hostIP = jsonAddress.getString("ip");
                String execTime = jsonAddress.getString("execution-time");
//                GEOGRAPHY
                JSONObject geo = jsonAddress.getJSONObject("geo");
                String countryName = geo.getString("country-name");
                String capital = geo.getString("capital");
                String iso = geo.getString("country-iso-code");
                String city = geo.getString("city");
                int zip = geo.getInt("zip-code");
                double longitude = geo.getDouble("longitude");
                double latitude = geo.getDouble("latitude");
//                  CURRENCY
                JSONObject currency = jsonAddress.getJSONObject("currency");
                String currencyNativeName = currency.getString("native-name");
                String currencyCode = currency.getString("code");
                String currencyName = currency.getString("name");
                String currencySymbol = currency.getString("symbol");
//                  ASN
                JSONObject asn = jsonAddress.getJSONObject("asn");
                String asnName = asn.getString("name");
                String asnDomain = asn.getString("domain");
                String asnOrganization = asn.getString("organization");
                String asnCode = asn.getString("asn");
                String asnType = asn.getString("type");
//                  TIMEZONE
                JSONObject timezone = jsonAddress.getJSONObject("timezone");
                String timezoneName = timezone.getString("microsoft-name");
                String dateTime = timezone.getString("date-time");
                String ianaName = timezone.getString("iana-name");
//                  SECURITY
                JSONObject security = jsonAddress.getJSONObject("security");
                boolean isCrawler = security.getBoolean("is-crawler");
                boolean isProxy = security.getBoolean("is-proxy");
                boolean isTor = security.getBoolean("is-tor");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            tv.setText(message);
        }
    }
}
