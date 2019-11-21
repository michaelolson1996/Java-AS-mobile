package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
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
                System.out.println("Response Code: " + httpClient.getResponseCode());
                int status = httpClient.getResponseCode();

                if (status != 200) {
                    return "no bueno";
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
            tv.setText(message);
        }
    }
}
