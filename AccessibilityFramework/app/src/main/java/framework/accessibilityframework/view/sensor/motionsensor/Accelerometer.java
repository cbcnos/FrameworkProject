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
 * The accelerometer is a motion sensor. All of the motion sensors return multi-dimensional
 * arrays of sensor values for each SensorEvent. In the case of the accelerometer, the three-dimensional
 * coordinates (event.values[0], event.values[1], event.values[2]) return the acceleration force, in m/s²
 * along the corresponding axis (0 for x, 1 for y, 2 for z).
 * Motion sensors are useful for monitoring device movement,
 * such as tilt, shake, rotation, or swing.
 * @see SensorUtils for documentation regarding detecting device movement
 */
public class Accelerometer extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor accelerometer;

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

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            workWithAccelerometer(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorUtils facade = SensorUtils.getInstance();
        try{
            accelerometer = facade.getSensorInstance(sensorManager, Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithAccelerometer(SensorEvent event){
    }
}
