package framework.accessibilityframework.control.environmentalsensor;

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
 * Unlike other environment sensors, the light sensor is common on mobile devices, because manufacturers
 * use it to control screen brightness.
 * The raw data you acquire from the light, pressure, and temperature sensors usually
 * requires no calibration, filtering, or modification.
 */
public class Thermometer extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;


    private Sensor thermometer;

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

        if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            workWithAmbientTemperature(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SensorFacade facade = SensorFacade.getInstance();
        try{
            thermometer = facade.getSensorInstance(sensorManager, Sensor.TYPE_AMBIENT_TEMPERATURE);
            sensorManager.registerListener(this, thermometer, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithAmbientTemperature(SensorEvent event) {}
}
