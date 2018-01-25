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
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;

/**
 * This class may be used to retrieve the last position of the user. If the call to onCreate is done at the current time,
 * the latitude and longitude will be the current coordinates of the user.
 * This class uses the features of the Google Location API, as reccomended by the official Android documentation.
 * In order to use Google's location API, it is necessary to include the instruction
 * implementation 'com.google.android.gms:play-services-location:11.8.0'
 * in the build.gradle file of the application module. If this class contains errors, please try adding the aformentioned
 * line to the gradle file and sync the project.
 * TODO consider removing the attributes that are not used by the app
 */

public class LocationActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQ_LOCATION = 5;
    private static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private boolean allPermissionsGranted = false;

    private double latitude; //current latitude of the device
    private double longitude; //current longitude of the device. (latitude, longitude) are one coordinate at the map

    private String address; //the address corresponding to the latitude and the longitude

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {

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
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {

                                    //TODO remove unecessary references
                                    // retrieve the latitude and longitude coordinations
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();

                                    address = LocationHelper.getAddressFromLocation(location, LocationActivity.this).get(0);

                                    TextView tv = findViewById(R.id.textRecognized);
                                    tv.setText("("+latitude+ ", "+ longitude+ ")\n"+address);
                                }
                            }
                        });
            }
        } catch (ActivityNotFoundException a) {

            Toast.makeText(LocationActivity.this,
                    "Opps! Your device doesn’t support Location Services", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(LocationActivity.this, LocationActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Opps! Your device doesn’t support Location Services" , Toast.LENGTH_LONG).show();
        }
    }
}

