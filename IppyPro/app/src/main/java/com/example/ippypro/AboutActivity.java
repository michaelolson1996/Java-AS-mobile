package com.example.ippypro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tv = findViewById(R.id.aboutTitle);
        TextView tv2 = findViewById(R.id.aboutContent);

        tv.setText("What is IppyPro?");
        tv2.setText("   IppyPro is an application designed to teach users about IP Addresses as" +
                " well as demonstrate the information you can gather with just access to an IP " +
                "Address. Currently with this application you are able to gather information provided " +
                "by SmartIP to get location, currency, security, Autonomous System Network, and more! " +
                "The goals with this project are to eventually be able to display on a map the location " +
                "of the IP address, as well as save IP address containers to a file. I hope you enjoy!");
    }


    public void setAboutDisplay(View ButtonID) {
        TextView tv = findViewById(R.id.aboutTitle);
        TextView tv2 = findViewById(R.id.aboutContent);
        String myButtonValue = String.valueOf(ButtonID.getId()).substring(7, 10);
        System.out.println(myButtonValue);
        switch(myButtonValue) {
            case "255":
                tv.setText("What is IppyPro?");
                tv2.setText("   IppyPro is an application designed to teach users about IP Addresses as" +
                        " well as demonstrate the information you can gather with just access to an IP " +
                        "Address. Currently with this application you are able to gather information provided " +
                        "by SmartIP to get location, currency, security, Autonomous System Network, and more! " +
                        "The goals with this project are to eventually be able to display on a map the location " +
                        "of the IP address, as well as save IP address containers to a file. I hope you enjoy!");
                break;
            case "256":
                tv.setText("Autonomous Network System");
                tv2.setText("   An autonomous System is a group of networks under a single administrative control, " +
                        "this can be a ISP (Internet Service Provider) or a large Enterprise Organization. An Interior " +
                        "Gateway Protocol is a routing protocol which handles routing within a single autonomous system. The " +
                        "ASN value 0 is reserved, as well as the largest ASN value 65,535. The values 1 to 64,511 are available, " +
                        "for use in Internet Routing, and the values 64,512 to 65,534 are for private use.");
                break;
            case "258":
                tv.setText("AsyncTask");
                tv2.setText("   The AsyncTask class in Java allows you to perform background operations without having to manipulate " +
                        "threads and/or handlers. This class is ideally used for short operations, several seconds at most. The task is " +
                        "designed by three generic types, called Params, Progress, and Result. It also has four steps, called onPreExecute, " +
                        "doInBackground, onProgressUpdate, and onPostExecute. Within the subclass which extends the AsyncTask class, is where you " +
                        "can perform a get request for APIs and create your HttpClient.");
                break;
            case "259":
                tv.setText("IP Address");
                tv2.setText("   An IP (Internet Protocol) address is a network address which tells the internet where to " +
                        "send information that either you request, or other devices wish to send to you. It was designed to allow " +
                        "one device to communicate with another device over the internet. Without the IP address we would not " +
                        "be able to communicate with each other via the internet. If you computer is connected to a local network as " +
                        "well as the internet, it will have 2 IP addresses, one which is visible on the web and one that marks the " +
                        "location of the local network."
                );
                break;
            default:
        }
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
