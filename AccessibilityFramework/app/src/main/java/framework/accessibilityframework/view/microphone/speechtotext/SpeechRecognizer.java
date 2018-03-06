package framework.accessibilityframework.view.microphone.speechtotext;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import framework.accessibilityframework.control.LocaleUtils;
import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;

/**
 * This class is responsible for displaying the mic symbol and recognizing the user's speech, which is
 * transformed into text and saved in an arraylist, which is a private attribute of the class.
 * There is a getter method in order to retrieve the text sentences externally.
 * This class functionality is NOT intended to be executed as a service, since its continuous use drains the device battery and
 * demands considerable bandwidth (the recognition is done online).
 * The recognition intent might ALWAYS be executed in the main thread of the application, or the Android framework will
 * throw an exception.
 * The recognized language is attribute of the class
 */

public class SpeechRecognizer extends AppCompatActivity {

    private String language = LocaleUtils.PORTUGUESE_BRASIL; //recognized language
    private static final int REQ_SPEECH = 3;
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    private boolean allPermissionsGranted = false;
    private ArrayList<String> recognized_sentences = new ArrayList<>(); //the text sentences recognized by the speech recognizer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);


        try {

            //handle necessary permissions for devices with Android 6.0 or above
            String[] record_perm = {PERMISSION_RECORD_AUDIO};
            if (Build.VERSION.SDK_INT >= 23 && ! allPermissionsGranted){ //has Android 6.0, but no permission was granted for recording audio

                if (PermissionUtils.checkPermission(this,PERMISSION_RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED){
                    PermissionUtils.requestPermission(this,record_perm, REQ_SPEECH);
                }

                if (PermissionUtils.checkPermission(this, PERMISSION_RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED){
                    allPermissionsGranted = true;
                }
            }

            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, language);

            startActivityForResult(intent, REQ_SPEECH);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(SpeechRecognizer.this,
                    "Opps! Your device doesn’t support Speech to Text", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {
                    //the sentences that were recognized by the speech recognizer are saved in the following arraylist,
                    //in descending order of accuracy according to the recording algorithm, ie, the sentence with the closest
                    //meaning will appear in position 0, the second sentence with the closest meaning will appear in position 1, and so on.
                    //For instance, if the user says "I'm going to live", the recognizer will probably return the sentence
                    //"I'm going to live" as the String in position 0 of the Arraylist, and will alson return the sentence
                    //"I'm going to leave" as the String in position 1 of the Arraylist

                    recognized_sentences = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String msg = "";
                    for (String s : recognized_sentences){
                        msg += s + "\n";
                    }

                    TextView tv = findViewById(R.id.textRecognized);
                    tv.setText(msg);


                }break;
            }
        }
    }

    public ArrayList<String> getRecognized_sentences() {
        return recognized_sentences;
    }
}
