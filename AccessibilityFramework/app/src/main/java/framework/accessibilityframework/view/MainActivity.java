package framework.accessibilityframework.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.microphone.audiorecorder.MicrophoneActivity;
import framework.accessibilityframework.control.microphone.speechtotext.SpeechRecognizer;
import framework.accessibilityframework.view.sensor.motionsensor.MotionEventActivity;
import framework.accessibilityframework.view.sensor.positionsensor.gps.ContinuousLocationActivity;
import framework.accessibilityframework.view.sensor.positionsensor.gps.LocationActivity;
import framework.accessibilityframework.view.camera.CustomCameraActivity;
import framework.accessibilityframework.view.camera.DefaultCameraActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup which_app; //radio group of all possible sample applications
    Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        which_app = (RadioGroup) findViewById(R.id.options_rg);
        goBtn = (Button) findViewById(R.id.go_btn);
    }

    @Override
    protected void onResume(){
        super.onResume();

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAppIntent;
                int selectedRadio = which_app.getCheckedRadioButtonId();

                if (selectedRadio == R.id.custom_camera_rb)
                    goToAppIntent = new Intent(MainActivity.this, CustomCameraActivity.class);
                else if (selectedRadio == R.id.camera_rb)
                    goToAppIntent = new Intent(MainActivity.this, DefaultCameraActivity.class);
                else if (selectedRadio == R.id.mic_rb)
                    goToAppIntent = new Intent(MainActivity.this, MicrophoneActivity.class);
                else if (selectedRadio == R.id.speechtotext)
                    goToAppIntent = new Intent(MainActivity.this, SpeechRecognizer.class);
                else if (selectedRadio == R.id.sensor_rb)
                    goToAppIntent = new Intent(MainActivity.this, SensorListManager.class);
                else if (selectedRadio == R.id.location_rb)
                    goToAppIntent = new Intent(MainActivity.this, LocationActivity.class);
                else if (selectedRadio == R.id.cont_location_rb)
                    goToAppIntent = new Intent(MainActivity.this, ContinuousLocationActivity.class);
                else
                    goToAppIntent = new Intent(MainActivity.this, MotionEventActivity.class);

                startActivity(goToAppIntent);
            }
        });


    }

}
