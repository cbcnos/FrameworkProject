package framework.accessibilityframework.view.sensor.motionsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.SensorFacade;

/**
 * The gravity sensor provides a three dimensional vector indicating the direction and magnitude
 * of gravity. Typically, this sensor is used to determine the device's relative orientation in space.
 * The units are the same as those used by the acceleration sensor (m/s2), and the coordinate system
 * is the same as the one used by the acceleration sensor.
 * @see Accelerometer for further documentation
 */
public class GravitySensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor gravitySensor;

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

        if (sensor.getType() == Sensor.TYPE_GRAVITY){
            workWithGravitySensor(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorFacade facade = SensorFacade.getInstance();
        try{
            gravitySensor = facade.getSensorInstance(sensorManager, Sensor.TYPE_GRAVITY);
            sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithGravitySensor(SensorEvent event){}
}