package com.example.ywu.flighttinder;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeScreenActivity extends AppCompatActivity{
    private Double latitude;
    private Double longitude;
    private WebView webView;

    private String uid1 = "123";
    private String uid2 = "321";
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

//        getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getActionBar().setCustomView(R.layout.action_bar);

        if (getApplicationContext().checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            MyLocationListener myLocationListener = new MyLocationListener();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, myLocationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            locationManager.removeUpdates(myLocationListener);

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.d("latitude", latitude.toString());
            Log.d("longtitude", longitude.toString());

            webView = (WebView) findViewById(R.id.webView);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new MyWebViewClient());
            uid = uid1;
            webView.loadUrl("http://10.222.200.64:8080/api/suggestion/getAirportInfo?lat=" + latitude.toString() + "&long=" + longitude.toString());
            Log.d("uid", uid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchUser(MenuItem item) {
        if (uid.equals(uid1)) {
            uid = uid2;
        } else {
            uid = uid1;
        }

        webView.loadUrl("http://10.222.200.64:8080/api/suggestion/getAirportInfo?lat=" + latitude.toString() + "&long=" + longitude.toString());
        Log.d("uid", uid);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            findViewById(R.id.progressView).setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }
    }

    public class MyLocationListener implements LocationListener {
        public void onLocationChanged(Location loc) {
        }

        public void onProviderDisabled(String arg0) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}
