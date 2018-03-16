package framework.accessibilityframework.view.sensor.positionsensor.gps;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;

/**
 * This class may be used in order to keep track of the user location updates. For example, if the user is walking in a certain city
 * and the application wants to keep track of his location updates.
 * It can also be used to calculate the device speed, by using the location services. In this case, you
 * must keep track of the gloab attributes oldTime, oldLat and oldLong and use them in getSpeed() function.
 * The location updates will occur every timeBetweenUpdates seconds, an attribute of the class.
 * In order to interrupt the updates, the developer must call method stopLocationUpdates() of the class. By default, this is done
 * when the user changes the activity, ie, when the method onPause() is called. Notice that this also occurs when the orientation of the
 * device changes
 *
 * This class uses the features of the Google Location API, as reccomended by the official Android documentation.
 * In order to use Google's location API, it is necessary to include the instruction
 * implementation 'com.google.android.gms:play-services-location:11.8.0'
 * in the build.gradle file of the application module. If this class contains errors, please try adding the aformentioned
 * line to the gradle file and sync the project.

 */
public class ContinuousLocationActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQ_LOCATION = 5;
    private static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private LocationCallback mLocationCallback;
    private LocationRequest locationRequest;
    private boolean allPermissionsGranted = false;
    private boolean mRequestingLocationUpdates = true;

    private double oldTime= 0; //this is the current time millis, used to calculate device speed
    private double oldLat = 0.0; //the old latitude of the location (the penultimate one)
    private double oldLon = 0.0; //the old longitude of the location (the penultimate one)

    private long timeBetweenUpdates = 30000; //time in MILLISECONDS between location updates. TODO Change this according to the app's needs

    ArrayList<String> addresses = new ArrayList<>(); //list of all addresses tracked. Position 0 contains the first address and the highest index of
    //the list contains the latest address identified. TODO discard this attribute if the addresses are not necessary

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            mLocationCallback = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    //Here is where the locations are identified!
                    for (Location location : locationResult.getLocations()) {
                        //The Location object contains many important attributes, such as the latitude and the longitude

                        // latestAddress keeps the latest address identified by the location provider
                        ArrayList<String> _latestAddress = LocationHelper.getAddressFromLocation(location, ContinuousLocationActivity.this);
                        String latestAddress = _latestAddress.get(0);

                        //Update here the global attributes oldTime, oldLat and oldLong if you want to keep
                        //track of the device speed. Then use the values in function getSpeed() of this class

                        TextView tv = findViewById(R.id.textRecognized);
                        tv.setText(latestAddress);

                        addresses.add(latestAddress);
                    }
                };
            };

            locationRequest = createLocationRequest();

            String[] record_perm = {PERMISSION_LOCATION};
            if (Build.VERSION.SDK_INT >= 23 && ! allPermissionsGranted){ //has Android 6.0, but no permission was granted for camera and storage

                if (PermissionUtils.checkPermission(this,PERMISSION_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){
                    PermissionUtils.requestPermission(this,record_perm, REQ_LOCATION);
                }

                if (PermissionUtils.checkPermission(this, PERMISSION_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    allPermissionsGranted = true;
                }
            }

            if (allPermissionsGranted) {
                if (mRequestingLocationUpdates) {
                    mFusedLocationClient.requestLocationUpdates(locationRequest,
                            mLocationCallback,
                            null /* Looper */);
                }

            }
        } catch (ActivityNotFoundException a) {
            Toast.makeText(ContinuousLocationActivity.this,
                    "Opps! Your device doesn’t support Location Services", Toast.LENGTH_SHORT).show();
        }
    }


    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(timeBetweenUpdates);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRequestingLocationUpdates = false;
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        if (!mRequestingLocationUpdates)
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    /**
     * Use this function to calculate the device speed, by using the location service. In order to use it,
     * you must update the values of global attributes
     * @param lastLocation - the last location identified by the location service
     * @param oldLatitude -  the latitude of the old location
     * @param oldLongitude -  the longitude of the old location
     * @param oldTimeMillis - the time when the old location (oldLatitude, oldLongitude) was retrieved
     */
    private void getspeed(Location lastLocation, float oldLatitude, float oldLongitude, double oldTimeMillis){
        double newTime= System.currentTimeMillis();
        double newLat = lastLocation.getLatitude();
        double newLon = lastLocation.getLongitude();
        if(lastLocation.hasSpeed()){ //in this case the api is able to retrieve the speed, in m/s
            float speed = lastLocation.getSpeed();
            Toast.makeText(getApplication(),"SPEED : "+String.valueOf(speed)+"m/s",Toast.LENGTH_SHORT).show();
        } else { //when the api is not able to calculate, we calculate the speed manually, by distance/time
            double distance = getDistance(newLat,newLon,oldLatitude,oldLongitude);
            double timeDifferent = newTime - oldTimeMillis;
            double speed = distance/timeDifferent;

            //here we update the values. You might to want to avoid this, depending on your application purposes
            oldTime = newTime;
            oldLat = newLat;
            oldLon = newLon;
            Toast.makeText(getApplication(),"SPEED 2 : "+String.valueOf(speed)+"m/s",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * this function calculates the distance between two coordinates (latitude, longitude). Another
     * way of doing this is by using the method location.getDistance() of the location service API.
     * @param lat1 - the latitude of the first location
     * @param lon1 - the longitude of the first location
     * @param lat2 - the latitude of the second location
     * @param lon2 - the longitude of the second location
     * @return
     */
    public double getDistance(double lat1, double lon1, double lat2, double lon2){
        double radius = 6371000; //the Earth radius
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return radius * c;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(ContinuousLocationActivity.this, LocationActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Opps! Your device doesn’t support Location Services" , Toast.LENGTH_LONG).show();
        }
    }
}

