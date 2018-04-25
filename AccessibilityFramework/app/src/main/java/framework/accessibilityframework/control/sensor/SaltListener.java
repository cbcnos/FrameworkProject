package framework.accessibilityframework.control.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This class is useful for detecting the emulation of salting movements, as if the device was a salt shaker.
 * The detection occurs when at least three shakes of the device are detected in three contiguous coordinate changes.
 * The idea of the shake detetcion is similar to whats happens to the ShakeListener.
 * @see ShakeListener for more details.
 * In order to use this class, you have to implement something like this:
 *
 SaltListener mShaker = new SaltListener(this);
 mShaker.setOnSaltListener(new SaltListener.OnSaltListener () {
 public void onSalt(){
    // your code to be executed when the device is abruptly shaken
 }


 */
public class SaltListener implements SensorEventListener {

    public static final int THRESHOLD = 4; //this is the shake threshold. Adjust this if you want to

    private int count = 0;
    private SensorManager mSensorManager;
    private float mAccel = 0.00f;
    private float mAccelCurrent = SensorManager.GRAVITY_EARTH;
    private float mAccelLast = SensorManager.GRAVITY_EARTH;

    private OnSaltListiner listener;

    //interface with the two methods for detecting salting simulation
    //When a variable sets OnSaltListener (as shown at the beginning of the class),
    //these events start to be detected automatically. You can then implement your code
    //after each event occurs.
    public interface OnSaltListiner {
        public void onSalt();
    }

    public SaltListener(Context c) {
        mSensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public SaltListener(Activity a) {
        mSensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public void setOnSaltListener(OnSaltListiner l){
        this.listener = l;
    }

    public void registerListener() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent se) {
        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];

        mAccelLast = mAccelCurrent; //the calculations are relative to the previous sensor values
        mAccelCurrent = (float) Math.sqrt(x*x + y*y + z*z);
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta; //amortization factor

        if(mAccel > THRESHOLD) {
            count++;
            if (count >= 2) {
                listener.onSalt();
            }
        }
        else{
            count = 0;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

}
