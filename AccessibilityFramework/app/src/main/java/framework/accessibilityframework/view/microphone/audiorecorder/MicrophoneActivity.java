package framework.accessibilityframework.control.microphone.audiorecorder;

/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 */

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;
import framework.accessibilityframework.view.camera.CustomCameraActivity;


public class MicrophoneActivity extends Activity
{
    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName;

    private MediaRecorder mRecorder;

    private Button recordButton;
    private Button playButton;

    private MediaPlayer mPlayer;

    private boolean startPlaying;
    private boolean startRecording;

    private static final String TAG = CustomCameraActivity.class.getName();
    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    private static final String PERMISSION_WRITE_SD = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final int REQ_RECORD = 1;
    private static final int REQ_SAVE_SD = 2;

    private boolean allPermissionsGranted = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microphone);
        recordButton = (Button) findViewById(R.id.recordBtn);
        playButton = (Button) findViewById(R.id.playBtn);


        String[] record_perm = {PERMISSION_RECORD_AUDIO};
        String[] sd_perms = {PERMISSION_WRITE_SD};
        if (Build.VERSION.SDK_INT >= 23 && !allPermissionsGranted){ //has Android 6.0, but no permission was granted for camera and storage

            if (PermissionUtils.checkPermission(this,PERMISSION_RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED){
                PermissionUtils.requestPermission(this,record_perm, REQ_RECORD);
            }

            if (PermissionUtils.checkPermission(this,PERMISSION_WRITE_SD)
                    != PackageManager.PERMISSION_GRANTED){
                PermissionUtils.requestPermission(this,sd_perms, REQ_SAVE_SD);
            }

            if (PermissionUtils.checkPermission(this,PERMISSION_WRITE_SD)== PackageManager.PERMISSION_GRANTED
                    && PermissionUtils.checkPermission(this,PERMISSION_RECORD_AUDIO)== PackageManager.PERMISSION_GRANTED){
                allPermissionsGranted = true;
            }
        }

        if (allPermissionsGranted || Build.VERSION.SDK_INT < 23) {

            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/audiorecord.3gp";
            mPlayer = new MediaPlayer();

            startRecording = startPlaying = true;

            recordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRecord(startRecording);
                    if (startRecording) {
                        recordButton.setText(R.string.stop_recording);
                        playButton.setEnabled(false);
                        playButton.setText(R.string.play);
                        if (!startPlaying) {
                            startPlaying = true;
                        }
                    } else {
                        recordButton.setText(R.string.record);
                        playButton.setEnabled(true);
                    }
                    startRecording = !startRecording;
                }
            });

            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlay(startPlaying);
                    if (startPlaying) {
                        playButton.setText(R.string.stop_playing);
                    } else {
                        playButton.setText(R.string.play);
                    }
                    startPlaying = !startPlaying;
                }
            });
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();

            //Listener for dealing with the audio when it has already finished playing
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    playButton.setText(R.string.play);
                }
            });
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(MicrophoneActivity.this, MicrophoneActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),getString(R.string.msg_req_perm) , Toast.LENGTH_LONG).show();
        }
    }
}
