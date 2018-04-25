package framework.accessibilityframework.control.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This class is useful for detecting abrupt and minor shake events. Please notice that
 * that these events are detected by methods onShake() and onLitlleShake(), respectively, and
 * that they are executed when the two thresholds SHAKE_LIMIT and LITTLE_SHAKE_LIMIT are exceeded.
 * In order to use this class, you have to implement something like this:
 *
 ShakeListener mShaker = new ShakeListener(this);
 mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
    public void onShake(){
        // your code to be executed when the device is abruptly shaken
    }

    public void onLittleShake(){
        // your code when the device is shaken a little
    }
 });

 */

public class ShakeListener implements SensorEventListener {
    public final static int SHAKE_LIMIT = 10; //this is the shake threshold. Adjust this if you want to
    public final static int LITTLE_SHAKE_LIMIT = 3; //this is the little shake threshold. Adjust this if you want to

    private SensorManager mSensorManager;
    private float mAccel = 0.00f;
    private float mAccelCurrent = SensorManager.GRAVITY_EARTH;
    private float mAccelLast = SensorManager.GRAVITY_EARTH;

    private OnShakeListener listener;

    //interface with the two methods for detecting shake
    //When a variable sets OnShakeListener (as shown at the beginning of the class),
    //these events start to be detected automatically. You can then implement your code
    //after each event occurs.
    public interface OnShakeListener {
        public void onShake();
        public void onLittleShake();
    }

    public ShakeListener(Context c) {
        mSensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public ShakeListener(Activity a) {
        mSensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public void setOnShakeListener(OnShakeListener l){
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
        if(mAccel > SHAKE_LIMIT)
            listener.onShake();
        else if(mAccel > LITTLE_SHAKE_LIMIT)
            listener.onLittleShake();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}