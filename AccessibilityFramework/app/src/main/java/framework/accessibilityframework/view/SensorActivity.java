package framework.accessibilityframework.view;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import framework.accessibilityframework.R;

/**
 * This class contains the specification of all sensors os a mobile device, except the gps, that has a different approach of use
 * It demonstrates how to get data from multiple sensors at the same time, which is different from whats is done in the single sensor
 * classes of packages positionsensor, motionsensor and environmetalsensor.
 * It communicates with elements of an xml-defined screen. TODO remove the unwanted references and change de xml according to the needs
 * The attribute timeToUpdate indicates that the data changed will be manipulated every timeToUpdate milliseconds. The time
 * approach here is not very precise, and may vary according to the device processors and tasks executed simultaneously.
 */
public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private String accel = "";
    //List of every possible sensors that the designer may use
    private Sensor accelerometer;
    private Sensor ambientTemperatureSensor;
    private Sensor gameRotationSensor;
    private Sensor geoMagneticRotationSensor;
    private Sensor gravitySensor;
    private Sensor gyroscope;
    private Sensor gyroscope_uncalibrated;
    private Sensor heartRateSensor;
    private Sensor humiditySensor;
    private Sensor lightSensor;
    private Sensor linearAccelerationSensor;
    private Sensor pressureSensor;
    private Sensor proximitySensor;
    private Sensor magneticFieldSensor;
    private Sensor magneticFieldUncalibrated;
    private Sensor rotationVectorSensor;
    private Sensor significantMotionSensor;
    private Sensor stepCounter;
    private Sensor stepDetector;

    private long timeToUpdate = 0; //time in milliseconds  between each sensor data usage.
    private long lastUpdateTime = 0; //the time of last sensor data use

    Integer chosen_sensor;

    TextView sensorName;
    TextView sensorDesc;
    TextView powerConsumption;
    TextView resolution;
    TextView range;
    TextView delay;
    TextView values;
    TextView unity;


    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);

        Intent intent = getIntent();


        chosen_sensor = intent.getIntExtra("chosen_sensor", -1);
        values = findViewById(R.id.values);
        sensorDesc = findViewById(R.id.sensorDescription);
        sensorName = findViewById(R.id.sensorName);
        powerConsumption = findViewById(R.id.powerConsumption);
        resolution = findViewById(R.id.resolution);
        range = findViewById(R.id.range);
        delay = findViewById(R.id.minDelay);
        unity = findViewById(R.id.unity);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lastUpdateTime = System.currentTimeMillis();

        //Instances of the sensors
        //TODO Developer may want to remove all references to unused sensors
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ambientTemperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE); //-
        gameRotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR); //-
        geoMagneticRotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR); //-
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY); //-
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyroscope_uncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED); //-
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE); //-
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY); //-
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION); //-
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY); //-
        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); //-
        magneticFieldUncalibrated = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED); //-
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR); //-
        significantMotionSensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION); //-
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER); //-
        stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR); //-
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            int type = sensor.getType();
            long timeNow = System.currentTimeMillis();

            if (timeNow - lastUpdateTime > timeToUpdate) {
                switch (type) {
                    case (Sensor.TYPE_ACCELEROMETER):
                        if (chosen_sensor == type)
                            workWithAccelerometer(event);
                        break;
                    case (Sensor.TYPE_AMBIENT_TEMPERATURE):
                        if (chosen_sensor == type)
                            workWithAmbientTemperature(event);
                        break;
                    case (Sensor.TYPE_GAME_ROTATION_VECTOR):
                        if (chosen_sensor == type)
                            workWithGameRotationVector(event);
                        break;
                    case (Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR):
                        if (chosen_sensor == type)
                            workWithGeomagneticRotationVector(event);
                        break;
                    case (Sensor.TYPE_GRAVITY):
                        if (chosen_sensor == type)
                            workWithGravitySensor(event);
                        break;
                    case (Sensor.TYPE_GYROSCOPE):
                        if (chosen_sensor == type)
                            workWithGyroscope(event);
                        break;
                    case (Sensor.TYPE_GYROSCOPE_UNCALIBRATED):
                        if (chosen_sensor == type)
                            workWithGyroscopeUncalibrated(event);
                        break;
                    case (Sensor.TYPE_HEART_RATE):
                        if (chosen_sensor == type)
                            workWithHeartRate(event);
                        break;
                    case (Sensor.TYPE_RELATIVE_HUMIDITY):
                        if (chosen_sensor == type)
                            workWithHumiditySensor(event);
                        break;
                    case (Sensor.TYPE_LIGHT):
                        if (chosen_sensor == type)
                            workWithLightSensor(event);
                        break;
                    case (Sensor.TYPE_LINEAR_ACCELERATION):
                        if (chosen_sensor == type)
                            workWithLinearAcceleration(event);
                        break;
                    case (Sensor.TYPE_PRESSURE):
                        if (chosen_sensor == type)
                            workWithPressureSensor(event);
                        break;
                    case (Sensor.TYPE_PROXIMITY):
                        if (chosen_sensor == type)
                            workWithProximitySensor(event);
                        break;
                    case (Sensor.TYPE_MAGNETIC_FIELD):
                        if (chosen_sensor == type)
                            workWithMagneticFieldSensor(event);
                        break;
                    case (Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED):
                        if (chosen_sensor == type)
                            workWithMagneticFieldUncalibrated(event);
                        break;
                    case (Sensor.TYPE_ROTATION_VECTOR):
                        if (chosen_sensor == type)
                            workWithRotationVector(event);
                        break;
                    case (Sensor.TYPE_SIGNIFICANT_MOTION):
                        if (chosen_sensor == type)
                            workWithSignificantMotion(event);
                        break;
                    case (Sensor.TYPE_STEP_COUNTER):
                        if (chosen_sensor == type)
                            workWithStepCounter(event);
                        break;
                    case (Sensor.TYPE_STEP_DETECTOR):
                        if (chosen_sensor == type)
                            workWithStepDetector(event);
                        break;
                }
                lastUpdateTime = System.currentTimeMillis();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Registers a listener for each sensor above.
        // TODO Remove lines concerning sensors that the app won't use

        super.onResume();

        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, ambientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gameRotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, geoMagneticRotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope_uncalibrated, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, heartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, linearAccelerationSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticFieldUncalibrated, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, significantMotionSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, stepDetector, SensorManager.SENSOR_DELAY_NORMAL);
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

    //TODO Implement methods
    protected void workWithAccelerometer(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.accelerometer));
        sensorDesc.setText(getString(R.string.accelerometer_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.ms2));
        accel += event.values[0]+", "+event.values[1]+", "+event.values[2] +"\n";
        values.setText(accel);
    }
    protected void workWithAmbientTemperature(SensorEvent event) {
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.ambient_temperature));
        sensorDesc.setText(getString(R.string.ambient_temperature_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("°C");
        values.setText(event.values[0]+"");
    }
    protected void workWithGameRotationVector(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.game_rotation));
        sensorDesc.setText(getString(R.string.game_rotation_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("quat.");
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithGeomagneticRotationVector(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.geomagnetic_rotation));
        sensorDesc.setText(getString(R.string.geomagnetic_rotation_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.microtesla));
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithGravitySensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.gravity_sensor));
        sensorDesc.setText(getString(R.string.gravity_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.ms2));
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithGyroscope(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.gyroscope));
        sensorDesc.setText(getString(R.string.gyroscope_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.rads));
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithGyroscopeUncalibrated(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.gyroscope_uncalibrated));
        sensorDesc.setText(getString(R.string.gyroscope_uncalibrated_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.rads));
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithHeartRate(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.heart_rate));
        sensorDesc.setText(getString(R.string.heart_rate_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("beats/min");
        values.setText(event.values[0]+"");
    }
    protected void workWithHumiditySensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.humidity_sensor));
        sensorDesc.setText(getString(R.string.humidity_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("%");
        values.setText(event.values[0]+"");
    }
    protected void workWithLightSensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.light_sensor));
        sensorDesc.setText(getString(R.string.light_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("lux");
        values.setText(event.values[0]+"");
    }
    protected void workWithLinearAcceleration(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.linear_acceleration));
        sensorDesc.setText(getString(R.string.linear_acceleration_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.ms2));
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithPressureSensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.pressure_sensor));
        sensorDesc.setText(getString(R.string.pressure_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("hPa or mbar");
        values.setText(event.values[0]+"");
    }
    protected void workWithProximitySensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.proximity_sensor));
        sensorDesc.setText(getString(R.string.proximity_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("cm");
        values.setText(event.values[0]+"");
    }
    protected void workWithMagneticFieldSensor(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.magnetic_field));
        sensorDesc.setText(getString(R.string.magnetic_field_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.microtesla));
        values.setText(event.values[0]+"");
    }
    protected void workWithMagneticFieldUncalibrated(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.magnetic_field_uncal));
        sensorDesc.setText(getString(R.string.magnetic_field_uncal_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText(getString(R.string.microtesla));
        values.setText(event.values[0]+"");
    }
    protected void workWithRotationVector(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.rotation_sensor));
        sensorDesc.setText(getString(R.string.rotation_sensor_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("quat");
        values.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]);
    }
    protected void workWithSignificantMotion(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.significant_motion));
        sensorDesc.setText(getString(R.string.significant_motion_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("N/A (No official documentation available)");
    }
    protected void workWithStepCounter(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.step_counter));
        sensorDesc.setText(getString(R.string.step_counter_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("step");
        values.setText(event.values[0]+"");
    }
    protected void workWithStepDetector(SensorEvent event){
        Sensor s = event.sensor;

        sensorName.setText(getString(R.string.step_detector));
        sensorDesc.setText(getString(R.string.step_detector_desc));
        powerConsumption.setText(s.getPower()+"");
        resolution.setText(s.getResolution()+"");
        range.setText(s.getMaximumRange()+"");
        delay.setText(s.getMinDelay()+"");
        unity.setText("");
        values.setText("N/A (No official documentation available)");
    }
}
