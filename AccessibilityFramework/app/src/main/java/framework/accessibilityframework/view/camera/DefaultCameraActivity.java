package framework.accessibilityframework.view.camera;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;
import framework.accessibilityframework.control.MediaUtils;


/*
 * This class can be used to open the existing Android camera app for taking a picture and/ or recording a video.
 * After taking the picture or recording, the app returns the result of the transaction to the current app
 */
public class DefaultCameraActivity extends Activity {

    private String directory = "DefaultCamera"; //directory in which the archive will be saved, in SD card

    private static final int IMAGE_CAPTURE_REQUEST = 1; //code to indicate that the user requested to take a picture
    private static final int VIDEO_CAPTURE_REQUEST = 2; //code to indicate that the user requested to record a video
    private static final String TAG = DefaultCameraActivity.class.getName();
    private static final String CAMERA_P = Manifest.permission.CAMERA;
    private static final String WRITE_SD = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private boolean allPermissionsGranted = false;

    private Uri mImageUri;
    private Uri mVideoUri;

    private ImageView iv;
    private VideoView vv;
    private Button recordVidBtn;
    private Button takePicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_app);

        iv = (ImageView) findViewById(R.id.imageView);
        takePicBtn = (Button) findViewById(R.id.takePicButn);
        vv = (VideoView) findViewById(R.id.videoView);
        recordVidBtn = (Button) findViewById(R.id.recordVidBtn);

        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        recordVidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordVideo();
            }
        });

        String[] cam_perms = {CAMERA_P};
        String[] sd_perms = {WRITE_SD};
        if (Build.VERSION.SDK_INT >= 23 && !allPermissionsGranted){ //has Android 6.0, but no permission was granted for camera and storage

            if (PermissionUtils.checkPermission(this,CAMERA_P)
                    != PackageManager.PERMISSION_GRANTED){
                PermissionUtils.requestPermission(this,cam_perms, IMAGE_CAPTURE_REQUEST);
            }

            if (PermissionUtils.checkPermission(this,WRITE_SD)
                    != PackageManager.PERMISSION_GRANTED){
                PermissionUtils.requestPermission(this,sd_perms, IMAGE_CAPTURE_REQUEST);
            }

            if (PermissionUtils.checkPermission(this,WRITE_SD)== PackageManager.PERMISSION_GRANTED
                    && PermissionUtils.checkPermission(this,WRITE_SD)== PackageManager.PERMISSION_GRANTED){
                allPermissionsGranted = true;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Takes a picture by using the default camera application of the device
     * The permissions to access the camera and to store data in external device must be previously
     * granted by the user.
     */
    public void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (allPermissionsGranted || Build.VERSION.SDK_INT < 23) {
            File photo;
            try {
                // place where to store camera taken picture
                // File here is always named picture.jpg, stored at a temporary directory ans is overwritten in case of a new shot
                //TODO adjust these parameters according to the app's needs
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                photo = MediaUtils.getOutputMediaFile(MediaUtils.MEDIA_TYPE_IMAGE, directory, "IMG_" + timeStamp + ".jpg");

                mImageUri = Uri.fromFile(photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);

                //start camera intent with request 0
                startActivityForResult(intent, IMAGE_CAPTURE_REQUEST);
            } catch (Exception e) {
                Log.e("FAIL", "Can't create file to take picture!");
                Toast.makeText(DefaultCameraActivity.this, "Please check SD card! Image shot is impossible!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Records a video by using the default camera application installed in the device.
     * The permissions to access the camera and to store data in external device must be previously
     * granted by the user.
     */
    public void recordVideo(){
        if (allPermissionsGranted || Build.VERSION.SDK_INT < 23) {
            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {

                //start video intent with request 1
                startActivityForResult(takeVideoIntent, VIDEO_CAPTURE_REQUEST);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (resultCode == RESULT_OK) { //the intent was completed correctly
                if (requestCode == IMAGE_CAPTURE_REQUEST) { //user has taken a picture
                    //TODO use the bitmap below if you want to work directly with the file
                    ContentResolver cr = this.getContentResolver();
                    Bitmap theFullImage = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);

                    //miniature image downsized to 1/5 of the original size
                    Bitmap miniatureImage = Bitmap.createScaledBitmap(theFullImage,theFullImage.getWidth()/5,
                            theFullImage.getHeight()/5, false);
                    iv.setVisibility(View.VISIBLE);
                    vv.setVisibility(View.INVISIBLE);
                    iv.setImageBitmap(miniatureImage);
                } else if (requestCode == VIDEO_CAPTURE_REQUEST) { //user has recorded a video
                    iv.setVisibility(View.INVISIBLE);
                    vv.setVisibility(View.VISIBLE);
                    mVideoUri = data.getData();

                    vv.setVideoURI(mVideoUri);
                    vv.start();
                }
            }
        }catch (IOException e){
            Log.e(TAG, "Error while saving the data.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent i = new Intent(DefaultCameraActivity.this, DefaultCameraActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),getString(R.string.msg_req_perm) , Toast.LENGTH_LONG).show();
        }
    }
}