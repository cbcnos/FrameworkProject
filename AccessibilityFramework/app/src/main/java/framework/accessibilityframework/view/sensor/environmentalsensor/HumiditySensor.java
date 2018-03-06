package framework.accessibilityframework.view.sensor.environmentalsensor;

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
 * You can acquire raw relative humidity data by using the humidity sensor the same way that
 * you use the light, pressure, and temperature sensors, ie, by using the value of event.values[0].
 * However, if a device has both a humidity sensor (TYPE_RELATIVE_HUMIDITY) AND a temperature sensor
 * (TYPE_AMBIENT_TEMPERATURE), you can use these two data streams to calculate the dew point
 * and the absolute humidity.
 * @see SensorUtils for further documentation on dew point and absolute humidity
 */
public class HumiditySensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor humiditySensor;

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
    /**
     * event[0] returns the value of the raw relative humidity, in percentage
     */
    public final void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
            workWithHumiditySensor(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SensorUtils facade = SensorUtils.getInstance();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        try{
            humiditySensor = facade.getSensorInstance(sensorManager, Sensor.TYPE_RELATIVE_HUMIDITY);
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithHumiditySensor(SensorEvent event){}
}

