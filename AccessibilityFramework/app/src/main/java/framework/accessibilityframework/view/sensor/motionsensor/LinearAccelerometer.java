package framework.accessibilityframework.view.sensor.motionsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.SensorUtils;

/**
 * The linear acceleration sensor provides you with a three-dimensional vector representing
 * acceleration along each device axis, excluding gravity. You can use this value to perform
 * gesture detection.
 * You typically use this sensor when you want to obtain acceleration data without the influence
 * of gravity. For example, you could use this sensor to see how fast your car is going.
 * The linear acceleration sensor always has an offset, which you need to remove.
 * The simplest way to do this is to build a calibration step into your application.
 * During calibration you can ask the user to set the device on a table, and then read the offsets
 * for all three axes. You can then subtract that offset from the acceleration sensor's direct readings
 * to get the actual linear acceleration.
 * The coordinates work similarly to the accelerometer's ones
 * @see Accelerometer for further documentation
 */
public class LinearAccelerometer extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;


    private Sensor linearAccelerometer;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            workWithAccelerometer(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorUtils facade = SensorUtils.getInstance();
        try{
            linearAccelerometer = facade.getSensorInstance(sensorManager, Sensor.TYPE_LINEAR_ACCELERATION);
            sensorManager.registerListener(this, linearAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    protected void workWithAccelerometer(SensorEvent event){}
}
