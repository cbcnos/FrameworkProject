package framework.accessibilityframework.view;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.SensorFacade;

/**
 * This class refers to the screen in which the user selects a radiobutton of the application he wants to look at.
 * The sensors retrieved in the listview are all the sensors that the manufacturer has made available for the device.
 * Frequently, many of the sensors lack official documentation of how to use in Android framework. In these cases, no data will
 * be displayed about them at the "selected sensor screen"
 */
public class SensorListManager extends AppCompatActivity {

    Intent sensorIntent;
    ListView sensorListView;
    SensorManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        sensorListView = findViewById(R.id.list_id);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Gets the instance of the facade class
        SensorFacade facade = SensorFacade.getInstance();

        //Restrieves list of all sensors available on the device
        final Integer[] available_sensors = facade.getDeviceSensorTypes(manager, SensorListManager.this);
        List<Sensor> sensors = facade.getAvailableDeviceSensors(manager, SensorListManager.this);

        ArrayList<String> sensor_strings = new ArrayList<String>();
        for (Sensor s: sensors){
            if (s != null) {
                sensor_strings.add(s.getName());
            }
        }

        String[] v = {"abc", "edf", "egs"};

        //Will redirect to SensorActivity screen
        sensorIntent = new Intent(SensorListManager.this, SensorActivity.class);

        //1st parameter: the context (Activity where we are)
        //2nd parameter: the model of one single line of the list
        //3rd parameter: id of the element to which the data is written
        //4th parameter: the array containing all the list's data
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this, R.layout.activity_sensor_list_schema,R.id.sensor_name, sensor_strings);

        sensorListView.setAdapter(adapters);

        sensorListView.setTextFilterEnabled(true);

        sensorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //extra data contaning which sensor was selected
                Integer aux = available_sensors[position];
                sensorIntent.putExtra("chosen_sensor",aux);
                startActivity(sensorIntent);
            }
        });
    }
}
