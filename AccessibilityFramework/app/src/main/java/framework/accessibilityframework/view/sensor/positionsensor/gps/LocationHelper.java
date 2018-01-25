package framework.accessibilityframework.view.sensor.positionsensor.gps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationHelper {

    public static ArrayList<String> getAddressFromLocation(Location location, Context context){
        String errorMessage = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            errorMessage = "IO Exception has been thrown while trying to retrieve address from location coordinates";
            // Catch network or other I/O problems.
            Log.e("error", errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            errorMessage = "Argument Exception has been thrown while trying to retrieve address from location coordinates ";
            // Catch invalid latitude or longitude values.
            Log.e("error",   errorMessage +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                return null;
            }
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            return addressFragments;
        }
        return null;
    }
}

