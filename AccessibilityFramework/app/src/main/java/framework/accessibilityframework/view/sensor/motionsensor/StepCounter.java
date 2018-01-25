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
 * Created by Olibario on 05/10/2017.
 */
public class StepCounter extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor stepCounter;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER); //-
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER){
            workWithStepCounter(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorFacade facade = SensorFacade.getInstance();
        try{
            stepCounter = facade.getSensorInstance(sensorManager, Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithStepCounter(SensorEvent event){}
}
