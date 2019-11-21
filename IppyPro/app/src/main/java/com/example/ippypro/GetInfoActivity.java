package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;

import java.io.BufferedReader;
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
        Bundle adresses = getIntent().getExtras();

        utilizeAddresses(adresses);
    }

    private class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection httpClient = null;
            try {
                Secret secret = new Secret();
                String configuredURL = "https://api.smartip.io/" + strings[0] + "?api_key=" + secret.getKey();
                URL url = new URL(configuredURL);
                httpClient = (HttpURLConnection) url.openConnection();
                httpClient.setRequestMethod("GET");
                httpClient.setConnectTimeout(4000);
                httpClient.setReadTimeout(4000);
                httpClient.connect();
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
                httpClient.disconnect();
                return e.toString();
            }
        }

        protected void onPostExecute(String result) {
            String query = result;

            try {
                JSONArray jsonArray = new JSONArray(query);

                for(int i = 0; i < jsonArray.length(); i++) {
                    String name = jsonArray.getJSONObject(i).getString("n");

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onPreExecute() {
            //nothing
        }
    }

    public void utilizeAddresses(Bundle addresses) {
        ArrayList<String> ipArr = addresses.getStringArrayList("addresses");
        String ipInfo = getIPAddressInfo(ipArr.get(0));
        TextView tv = findViewById(R.id.display);
        tv.setText(ipInfo);
    }

    public String getIPAddressInfo(String address) {
        return null;
    }
}
