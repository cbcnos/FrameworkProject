package framework.accessibilityframework.control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import java.util.HashMap;
import java.util.List;

import framework.accessibilityframework.R;

/**
 * Created by Olibario on 17/02/2016.
 */
public class SensorFacade {

    /** User-friendly names for the sensors **/
    /**Orientation, temperature, glance gesture, pick-up gesture, tilt detector and wake gesture **/

    /** MOTION SENSORS **/
    public static final String ACCELEROMETER = "accelerometer";
    public static final String GYROSCOPE = "gyroscope";
    public static final String GYROSCOPE_UNCALIBRATED = "gyroscope uncalibrated";
    public static final String GRAVITY = "gravity";
    public static final String ROTATION_VECTOR = "rotation vector";
    public static final String SIGNIFICANT_MOTION = "signitifcant motion";
    public static final String STEP_DETECTOR = "step detector";
    public static final String STEP_COUNTER = "step counter";

    /** POSITION SENSORS **/
    public static final String MAGNETIC_FIELD = "magnetic field";
    public static final String MAGNETIC_FIELD_UNCALIBRATED ="magnetic field uncalibrated";
    public static final String PROXIMITY = "proximity";
    public static final String GAME_ROTATION_VECTOR = "game rotation vector";
    public static final String GEOMAGNETIC_ROTATION_VECTOR = "geomagnetic rotation vector";

    /** ENVIRONMENT SENSORS **/
    public static final String LIGHT = "light";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String AMBIENT_TEMPERATUE = "ambient temperature";

    private static HashMap<Integer,String> allPossibleSensorNames; //contains the name of all possible Android sensors,
                   // according to the names above. These are NOT the sensors the user's device is equipped with.
                   //For that purpose, please refer to functions getDeviceSensors() and getDeviceSensorsAsArray()

    private static SensorFacade facade;

    public static SensorFacade getInstance(){
        populateSensorNames();
        if (facade == null){
            facade = new SensorFacade();
        }
        return facade;
    }

    /**
     * populates the hashmap with all possible sensor names the device may be equipped with
     */
    private static void populateSensorNames(){
        if (allPossibleSensorNames == null){
            allPossibleSensorNames = new HashMap<>();
            allPossibleSensorNames.put(Sensor.TYPE_ACCELEROMETER, ACCELEROMETER);
            allPossibleSensorNames.put(Sensor.TYPE_AMBIENT_TEMPERATURE, AMBIENT_TEMPERATUE);
            allPossibleSensorNames.put(Sensor.TYPE_GAME_ROTATION_VECTOR, GAME_ROTATION_VECTOR);
            allPossibleSensorNames.put(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR, GEOMAGNETIC_ROTATION_VECTOR);
            allPossibleSensorNames.put(Sensor.TYPE_GYROSCOPE, GYROSCOPE);
            allPossibleSensorNames.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, GYROSCOPE_UNCALIBRATED);
            allPossibleSensorNames.put(Sensor.TYPE_GRAVITY, GRAVITY);
            allPossibleSensorNames.put(Sensor.TYPE_LIGHT, LIGHT);
            allPossibleSensorNames.put(Sensor.TYPE_MAGNETIC_FIELD, MAGNETIC_FIELD);
            allPossibleSensorNames.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, MAGNETIC_FIELD_UNCALIBRATED);
            allPossibleSensorNames.put(Sensor.TYPE_PRESSURE, PRESSURE);
            allPossibleSensorNames.put(Sensor.TYPE_PROXIMITY, PROXIMITY);
            allPossibleSensorNames.put(Sensor.TYPE_RELATIVE_HUMIDITY, HUMIDITY);
            allPossibleSensorNames.put(Sensor.TYPE_ROTATION_VECTOR, ROTATION_VECTOR);
            allPossibleSensorNames.put(Sensor.TYPE_SIGNIFICANT_MOTION, SIGNIFICANT_MOTION);
            allPossibleSensorNames.put(Sensor.TYPE_STEP_COUNTER, STEP_COUNTER);
            allPossibleSensorNames.put(Sensor.TYPE_STEP_DETECTOR, STEP_DETECTOR);
        }
    }

    public static HashMap<Integer, String> getAllPossibleSensorNames() {
        populateSensorNames();
        return allPossibleSensorNames;
    }

    /**
     * Checks if a certain sensor is available at the device
     * @param manager - the sensor manager
     * @param sensorType - the sensor type. This value is always a constant of the form Sensor.TYPE_<sensorType>
     * @return true if the device is equipped with the sensor. False if not.
     */
    public boolean checkSensorAvailability(SensorManager manager, int sensorType){
        if (manager.getDefaultSensor(sensorType) != null){
            return true;
        }
        return false;
    }

    /**
     * If the device is equipped with the sensor, returns an instance of the sensor, which can be used by
     * the developer.
     * @param manager - the sensor manager
     * @param sensorType - the sensor type of the sensor to be instantiated.
     *                   This value is always a constant of the form Sensor.TYPE_<sensorType>
     * @return the sensor
     * @throws Exception if the device is not equipped with the sensor
     */
    public Sensor getSensorInstance(SensorManager manager, int sensorType) throws Exception {
        Sensor aux = manager.getDefaultSensor(sensorType);
        if (aux != null){
            return aux;
        }
        else {
            throw new Exception("The device is not equipped with " +
                    allPossibleSensorNames.get(sensorType)+ " sensor.");
        }
    }

    /**
     * This functions returns all sensors the device supports. This includes all position, motion and environment sensors
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service 
     */
    public String getDeviceSensors(SensorManager manager, Context context){
        String result = "";
        HashMap<Integer, String> map = getAllPossibleSensorNames();

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensors){
            result+= map.get(s.getType()) + "\n";
        }
        return result;
    }

    /**
     * This functions returns all sensors the device supports, according to the idiom of the user's device.
     * This includes all position, motion and environment sensors
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service
     */
    public String getDeviceSensorsInUserLanguage(SensorManager manager, Context context){
        String result = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    result+= context.getString(R.string.accelerometer)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    result+= context.getString(R.string.gyroscope)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                    result+= context.getString(R.string.gyroscope_uncalibrated)+"\n";
                    break;
                case Sensor.TYPE_GRAVITY:
                    result+= context.getString(R.string.gravity_sensor)+"\n";
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    result+= context.getString(R.string.rotation_sensor)+"\n";
                    break;
                case Sensor.TYPE_SIGNIFICANT_MOTION:
                    result+= context.getString(R.string.significant_motion)+"\n";
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    result+= context.getString(R.string.step_detector)+"\n";
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    result+= context.getString(R.string.step_counter)+"\n";
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    result+= context.getString(R.string.magnetic_field)+"\n";
                    break;
                case Sensor.TYPE_PROXIMITY:
                    result+= context.getString(R.string.proximity_sensor)+"\n";
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    result+= context.getString(R.string.magnetic_field_uncal)+"\n";
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    result+= context.getString(R.string.game_rotation)+"\n";
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    result+= context.getString(R.string.geomagnetic_rotation)+"\n";
                    break;
                case Sensor.TYPE_LIGHT:
                    result+= context.getString(R.string.light_sensor)+"\n";
                    break;
                case Sensor.TYPE_PRESSURE:
                    result+= context.getString(R.string.pressure_sensor)+"\n";
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    result+= context.getString(R.string.ambient_temperature)+"\n";
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    result+= context.getString(R.string.humidity_sensor)+"\n";
                    break;
            }
        }
        return result;
    }

    /**
     * This functions returns all sensors of the device supports. This includes all position, motion and environment sensors.
     * Each position of the array contains the name of one sensor that the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service
     */
    public List<Sensor> getAvailableDeviceSensors(SensorManager manager, Context context){

        return manager.getSensorList(Sensor.TYPE_ALL);
    }

    /**
     * This functions returns all sensors the device supports. This includes all position, motion and environment sensors.
     * Each position of the array contains the name of one sensor that the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service 
     */
    public String[] getDeviceSensorsAsArray(SensorManager manager, Context context){
        String result[] = new String[25];
        int i = 0;

        HashMap<Integer, String> map = getAllPossibleSensorNames();

        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: sensors){
            result[i]= map.get(s.getType());
            i++;
        }

        return result;
    }

    /**
     * This functions returns all sensor types of teh sensors the device supports. This includes all position, motion and environment sensors.
     * Each position of the array contains the name of one sensor that the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service
     */
    public Integer[] getDeviceSensorTypes(SensorManager manager, Context context){
        Integer[] result = new Integer[25];
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i < sensors.size(); i++){
            result[i]= sensors.get(i).getType();
        }

        return result;
    }
    
    /**
     * This functions returns all position sensors the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     *  @param context - the reference to the calling activity or service               
     */
    public String getPositionSensors(SensorManager manager, Context context){
        String result = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_MAGNETIC_FIELD:
                    result+= context.getString(R.string.magnetic_field)+"\n";
                    break;
                case Sensor.TYPE_PROXIMITY:
                    result+= context.getString(R.string.proximity_sensor)+"\n";
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    result+= context.getString(R.string.magnetic_field_uncal)+"\n";
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    result+= context.getString(R.string.game_rotation)+"\n";
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    result+= context.getString(R.string.geomagnetic_rotation)+"\n";
                    break;
            }
        }
        return result;
    }

    /**
     * This functions returns all position sensors the device supports, as a vector. Each position contains one sensor.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     *  @param context - the reference to the calling activity or service               
     */
    public String[] getPositionSensorsAsArray(SensorManager manager, Context context){
        String result[] = new String[5];
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        int i = 0;
        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_MAGNETIC_FIELD:
                    result[i] = context.getString(R.string.magnetic_field)+"\n";
                    break;
                case Sensor.TYPE_PROXIMITY:
                    result[i] = context.getString(R.string.proximity_sensor)+"\n";
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                    result[i] = context.getString(R.string.magnetic_field_uncal)+"\n";
                    break;
                case Sensor.TYPE_GAME_ROTATION_VECTOR:
                    result[i] = context.getString(R.string.game_rotation)+"\n";
                    break;
                case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                    result[i] = context.getString(R.string.geomagnetic_rotation)+"\n";
                    break;
            }
            i++;
        }
        return result;
    }
    
    /**
     * This functions returns all motion sensors the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service                
     */
    public String getMotionSensors(SensorManager manager, Context context){
        String result = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    result+= context.getString(R.string.accelerometer)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    result+= context.getString(R.string.gyroscope)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                    result+= context.getString(R.string.gyroscope_uncalibrated)+"\n";
                    break;
                case Sensor.TYPE_GRAVITY:
                    result+= context.getString(R.string.gravity_sensor)+"\n";
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    result+= context.getString(R.string.rotation_sensor)+"\n";
                    break;
                case Sensor.TYPE_SIGNIFICANT_MOTION:
                    result+= context.getString(R.string.significant_motion)+"\n";
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    result+= context.getString(R.string.step_detector)+"\n";
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    result+= context.getString(R.string.step_counter)+"\n";
                    break;
            }
        }
        return result;
    }

    /**
     * This functions returns all motion sensors the device supports, as an array. Each position contains one sensor name.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service                
     */
    public String[] getMotionSensorsAsArray(SensorManager manager, Context context){
        String result[] = new String[8];
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        int i = 0;
        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    result[i] = context.getString(R.string.accelerometer)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    result[i] = context.getString(R.string.gyroscope)+"\n";
                    break;
                case Sensor.TYPE_GYROSCOPE_UNCALIBRATED:
                    result[i] = context.getString(R.string.gyroscope_uncalibrated)+"\n";
                    break;
                case Sensor.TYPE_GRAVITY:
                    result[i] = context.getString(R.string.gravity_sensor)+"\n";
                    break;
                case Sensor.TYPE_ROTATION_VECTOR:
                    result[i] = context.getString(R.string.rotation_sensor)+"\n";
                    break;
                case Sensor.TYPE_SIGNIFICANT_MOTION:
                    result[i] = context.getString(R.string.significant_motion)+"\n";
                    break;
                case Sensor.TYPE_STEP_DETECTOR:
                    result[i] = context.getString(R.string.step_detector)+"\n";
                    break;
                case Sensor.TYPE_STEP_COUNTER:
                    result[i] = context.getString(R.string.step_counter)+"\n";
                    break;
            }
            i++;
        }
        return result;
    }
    

    /**
     * This functions returns all environment sensors the device supports.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service                
     */
    public String getEnvironmentSensors(SensorManager manager, Context context){
        String result = "";
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_LIGHT:
                    result+= context.getString(R.string.light_sensor)+"\n";
                    break;
                case Sensor.TYPE_PRESSURE:
                    result+= context.getString(R.string.pressure_sensor)+"\n";
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    result+= context.getString(R.string.ambient_temperature)+"\n";
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    result+= context.getString(R.string.humidity_sensor)+"\n";
                    break;
            }
        }
        return result;
    }

    /**
     * This functions returns all environment sensors the device supports, as an array. Each position contains the name of one sensor.
     * @param manager - a previously-instantiated manager (Usually instantiated via getSystemService(SENSOR_SERVICE) command
     *                in an activity
     * @param context - the reference to the calling activity or service                
     */
    public String[] getEnvironmentSensorsAsArray(SensorManager manager, Context context){
        String result[] = new String[4];
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ALL);
        int i = 0;
        for (Sensor s: sensors){
            switch (s.getType()){
                case Sensor.TYPE_LIGHT:
                    result[i] = context.getString(R.string.light_sensor)+"\n";
                    break;
                case Sensor.TYPE_PRESSURE:
                    result[i] = context.getString(R.string.pressure_sensor)+"\n";
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    result[i] = context.getString(R.string.ambient_temperature)+"\n";
                    break;
                case Sensor.TYPE_RELATIVE_HUMIDITY:
                    result[i] = context.getString(R.string.humidity_sensor)+"\n";
                    break;
            }
            i++;
        }
        return result;
    }

    /**
     * Returns the angular speed of the device. This speed comprises all coordinates in only one value.
     * @param event - the sensor object with coordinates
     * @return - the angular speed
     */
    public float getAngularSpeed(SensorEvent event){
        return (float) Math.sqrt(event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]);
    }

    /**
     * Normalize the rotation vector if it's big enough to get the axis
     * @param event - the sensor coordinates
     * @return
     */
    public float[] normalizeRotationCoords(SensorEvent event){
        float[] g = event.values;

        float norm_Of_g = getAngularSpeed(event);

        // Normalize the accelerometer vector
        g[0] = g[0] / norm_Of_g;
        g[1] = g[1] / norm_Of_g;
        g[2] = g[2] / norm_Of_g;

        return g;
    }

    /**
     * Retrieves the degrees (in º) the device is rotated relative to axis X.
     * @param event - the sensor coordinates
     * @return - the rotation  in degrees
     */
    public float getX_RotationDegree(SensorEvent event){
        return (Math.round(Math.toDegrees(Math.acos(event.values[0]))));
    }

    /**
     * Retrieves the degrees (in º) the device is rotated relative to axis Y.
     * @param event - the sensor coordinates
     * @return - the rotation  in degrees
     */
    public float getY_RotationDegree(SensorEvent event){
        return (Math.round(Math.toDegrees(Math.acos(event.values[1]))));
    }

    /**
     * Retrieves the degrees (in º) the device is rotated relative to axis Z.
     * @param event - the sensor coordinates
     * @return - the rotation  in degrees
     */
    public float getZ_RotationDegree(SensorEvent event){
        return (Math.round(Math.toDegrees(Math.acos(event.values[2]))));
    }

    /**
     * Returns the degrees (in °) that the device is rotated relative to axes X, Y and Z.
     * @param event - the sensor  coordinates
     * @return - the rotations in degrees
     */
    public float[] getRotationDegrees(SensorEvent event){
        float [] result = event.values.clone();
        result[0] = Math.round(Math.toDegrees(Math.acos(event.values[0])));
        result[1] = Math.round(Math.toDegrees(Math.acos(event.values[1])));
        result[2] = Math.round(Math.toDegrees(Math.acos(event.values[2])));

        return result;
    }


    /********************************************************************************************************/
    /************************** ENVIRONMENTAL SENSORS FUNCTIONALITIES ***************************************/
    /********************************************************************************************************/


    /********************************************************************************************************/
    /***************************** THERMOMETER **************************************************************/
    /********************************************************************************************************/

    /**
     * Gets the temperature in Celsius degrees
     * @param event - the sensor coordinates
     * @return - the temperature in Celsius degrees
     *         - null case there is no temperature captured by the sensor
     */
    public Float getTemperatureCelsius(SensorEvent event){
        Sensor theSensor  = event.sensor;
        if (theSensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            return event.values[0];
        }
        return null;
    }

    /**
     * Gets the temperature in Fahrenheit
     * @param event - the sensor coordinates
     * @return - the temperature in Fahrenheit
     *         - null case there is no temperature captured by the sensor
     */
    public Float getTemperatureFahrenheit(SensorEvent event){
        Sensor theSensor  = event.sensor;
        if (theSensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            return (event.values[0]*1.8f + 32);
        }
        return null;
    }


    /********************************************************************************************************/
    /***************************** HUMIDITY SENSOR **********************************************************/
    /********************************************************************************************************/

    /**
     * Returns the raw relative humidity, in percentage.
     * @param event - the sensor event object, containing the coordinate
     * @return - the raw relative humidity, in percentage.
     */
    public Float getHumidity(SensorEvent event){
        return event.values[0];
    }

    /**
     * Returns the dew point. The dew point is the temperature at which a given volume of air must be cooled,
     * for water vapor to condense into water. This requires that the device has both humidity sensor
     * and thermometer.
     * @param humidityEvent - the humidity sensor event object, containing the coordinate
     * @param thermometerEvent - the thermometer sensor event object, containing the coordinate of the temperature
     *                      in degrees C.
     * @return - the dew point, or null if the device does not support thermometer or humidity sensor.
     */
    public Double getDewPoint(SensorEvent humidityEvent, SensorEvent thermometerEvent){
        if ((humidityEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY)&&
                (thermometerEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE)){

            float tempC = thermometerEvent.values[0];
            float humidityPerc = humidityEvent.values[0];
            double baseCalculation = (Math.log(humidityPerc/100) + (17.62*tempC)/(243.12 + tempC));

            return (243.12 * baseCalculation)/(17.62 - baseCalculation);
        }
        return null;
    }

    /**
     * Returns the absolute humidity, in grams/meter³. The absolute humidity is the mass of water vapor
     * in a given volume of dry air.
     * @param humidityEvent - the humidity sensor event object, containing the coordinate
     * @param thermometerEvent - the thermometer sensor event object, containing the coordinate of the temperature
     *                      in degrees C.
     * @return the absolute humidity, in grams/meter³
     */
    public double getAbsoluteHumidity(SensorEvent humidityEvent, SensorEvent thermometerEvent){
        float tempC = thermometerEvent.values[0];
        float humidityPerc = humidityEvent.values[0]/100;
        double expo = Math.exp((17.62*tempC)/(243.12+tempC));

        return (1324.4704 *humidityPerc*expo)/ (273.15 + tempC);
     }


}
