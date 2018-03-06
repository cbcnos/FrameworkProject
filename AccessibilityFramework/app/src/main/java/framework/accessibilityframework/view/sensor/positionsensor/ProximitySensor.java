package framework.accessibilityframework.view.sensor.positionsensor;

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
 *
 */
public class ProximitySensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor proximitySensor;

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

        if (sensor.getType() == Sensor.TYPE_PROXIMITY){
            workWithProximitySensor(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorUtils facade = SensorUtils.getInstance();
        try{
            proximitySensor = facade.getSensorInstance(sensorManager, Sensor.TYPE_PROXIMITY);
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
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


    protected void workWithProximitySensor(SensorEvent event){}
}
