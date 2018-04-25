package framework.accessibilityframework.control.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This class is useful for detecting letaral movements of the smartphone. It assumes that the device is not
 * changing its orientation while the dtection is being held. If the changing of orientation happens, then
 * a refreshing of data is done and detection restarts ate the new orientation.
 * The lateral movement here is identified by the variation of the x values. If the x variation is greater than a threshold,
 * and the y and z variations are small, that a lateral movement might have happened. The threshold is 100 by default and
 * can be changed according to the needs. Notice that the variation is always positive, because values are multiplied
 * to the power of 2 and no square root calculation is performed. That means (roughly) a variation of about 10 unities
 * to each coordinate.
 * In order to use this class, you have to implement something like this:
 *
 LateralMovementListener lateralMovementListener = new LateralMovementListener(this);
 lateralMovementListener.setOnLateralMovementListener(new LateralMovementListener.OnLateralMoveListener() {
    @Override
    public void onLateralMovement() {
        //your code!
    }
 */

public class LateralMovementListener implements SensorEventListener {
    public final static int THRESHOLD = 81; //the threshold of detection

    private SensorManager mSensorManager;
    private int count = 0;

    private OnLateralMoveListener listener;

    private float xVariation; //the variation of the first coordinate
    private float yVariation; //the variation of the second coordinate
    private float zVariation; //the variation of the third coordinate

    //interface with the two methods for detecting shake
    //When a variable sets OnShakeListener (as shown at the beginning of the class),
    //these events start to be detected automatically. You can then implement your code
    //after each event occurs.
    public interface OnLateralMoveListener {
        public void onLateralMovement();
    }

    public LateralMovementListener(Context c) {
        mSensorManager = (SensorManager) c.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public LateralMovementListener(Activity a) {
        mSensorManager = (SensorManager) a.getSystemService(Context.SENSOR_SERVICE);
        registerListener();
    }

    public void setOnLateralMovementListener(OnLateralMoveListener l){
        this.listener = l;
    }

    public void registerListener() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterListener() {
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent se) {

        //the calculations are relative to the previous sensor values
        xVariation = se.values[0]*se.values[0] - xVariation;
        yVariation = se.values[1]*se.values[1] - yVariation;
        zVariation = se.values[2]*se.values[2] - zVariation;

        if (xVariation >= THRESHOLD && yVariation < THRESHOLD && zVariation < THRESHOLD &&
                count > 0){
            listener.onLateralMovement();
        }

        count++;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
