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
 * The rotation vector represents the orientation of the device as a combination of an angle and an axis,
 * in which the device has rotated through an angle θ around an axis (x, y, or z).
 */
public class RotationVector extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private Sensor rotationVectorSensor;

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

        if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            workWithRotationVector(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SensorFacade facade = SensorFacade.getInstance();
        try{
            rotationVectorSensor = facade.getSensorInstance(sensorManager, Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
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

    protected void workWithRotationVector(SensorEvent event) {

    }

}
