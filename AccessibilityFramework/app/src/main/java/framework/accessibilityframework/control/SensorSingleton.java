package framework.accessibilityframework.control;

/**
 * This singleton class is usable for storing sensor data temporarily for processing
 * It contains attributes that are useful for maintaining the state of sensors,
 * such as the timestamp of every value of the sensor. Currently, it only stores
 * the data of one single sensor.
 */

public class SensorSingleton {

    float[] values;
    long timeStamp;

    private static SensorSingleton theSingleton;

    public static SensorSingleton getInstance(){
        if (theSingleton == null){
            return new SensorSingleton();
        }
        return theSingleton;
    }

    public float[] getValues() {
        return values;
    }

    public void setValues(float[] values) {
        this.values = values;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
