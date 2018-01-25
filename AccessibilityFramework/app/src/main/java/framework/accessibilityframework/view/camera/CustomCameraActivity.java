package framework.accessibilityframework.view.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import framework.accessibilityframework.control.PermissionUtils;
import framework.accessibilityframework.R;
import framework.accessibilityframework.control.MediaUtils;

/**
 * This class controls the custom camera application. This application contains a preview of the camera in fullscreen, a button to
 * take pictures and an imageview for displaying a thumbnail of the picture thar was taken. All pictures taken are saved into external
 * storage directory, by using the storage functions of class
 * When taking a picture in a custom camera, the image may be shown to the user at the wrong orientation if he changes portrait/landscape
 * orientation of the device. For this reason, this class makes rotations to the original bitmap of the picture, which slows daown the
 * process of displaying the final image to the user. If this is not a problema in your application, consider removing the code corresponding
 * to these transformations (lines marked with TODO)
 * taking the picture is done by the function takePicture()
 * @see MediaUtils
 */
@SuppressWarnings("deprecation")
public class CustomCameraActivity extends AppCompatActivity {

    private String directory = "CustomCameraApp"; //directory in the sd where the image is saved

    private static final String TAG = CustomCameraActivity.class.getName();
    private static final String CAMERA_P = Manifest.permission.CAMERA;
    private static final String WRITE_SD = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final int REQ_CAMERA = 1;
    private static final int REQ_WRITE_SD = 2;
    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private boolean noPermissionsGranted = true;
    private int imgDegrees;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);

        // Create an instance of Camera
        try {
            String[] perms = {CAMERA_P};
            if (Build.VERSION.SDK_INT >= 23 && noPermissionsGranted){ //has Android 6.0, but no permission was granted for camera and storage

                if (PermissionUtils.checkPermission(this,CAMERA_P)
                    != PackageManager.PERMISSION_GRANTED){
                    PermissionUtils.requestPermission(this,perms, REQ_CAMERA);
                }
                else{ //user already granted the permissions
                    noPermissionsGranted = false;
                }
            }

            if (!noPermissionsGranted || Build.VERSION.SDK_INT < 23){
                noPermissionsGranted = true;
                open(findFrontFacingCameraID());

                imgDegrees = setCameraDisplayOrientation(this, findFrontFacingCameraID(), mCamera);

                // Create our Preview view and set it as the content of our activity.
                mPreview = new CameraPreview(this, mCamera);
                FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                preview.addView(mPreview);

                mPicture = new Camera.PictureCallback() {

                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {

                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                        //The picture file name will be formed by the timestamp. Change this according to the needs.
                        File pictureFile = MediaUtils.getOutputMediaFile(MediaUtils.MEDIA_TYPE_IMAGE, directory,
                                timeStamp+".jpg");
                        if (pictureFile == null){
                            Log.d(TAG, "Error creating media file, check storage permissions: ");
                            return;
                        }

                        try {
                            FileOutputStream fos = new FileOutputStream(pictureFile);
                            Toast.makeText(CustomCameraActivity.this, getString(R.string.msg_photo_taken)+ " "+
                                pictureFile.getPath(), Toast.LENGTH_LONG).show();

                            //convert the array of bytes into a bitmap
                            Bitmap myBitmap = BitmapFactory.decodeByteArray(data,0,data.length);

                            Bitmap rotatedImg = mirrorAndRotate(myBitmap, imgDegrees);

                            //load thumbnail of last picture taken
                            ImageView iv = (ImageView) findViewById(R.id.imgIV);
                            iv.setImageBitmap(rotatedImg);

                            //save the image to file
                            rotatedImg.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            fos.close();

                            //avoids the screen from freezing, allowing the user to take another picture
                            mCamera.startPreview(); } catch (FileNotFoundException e) {
                            Log.e(TAG, "File not found: " + e.getMessage());
                        } catch (IOException e) {
                            Log.e(TAG, "Error accessing file: " + e.getMessage());
                        }
                    }
                };

                perms[0] = WRITE_SD;
                if (Build.VERSION.SDK_INT >= 23 && noPermissionsGranted){ //has Android 6.0, but no permission was granted for camera and storage

                    if (PermissionUtils.checkPermission(CustomCameraActivity.this,WRITE_SD)
                            != PackageManager.PERMISSION_GRANTED){
                        PermissionUtils.requestPermission(CustomCameraActivity.this,perms, REQ_WRITE_SD);
                    }
                    else{ //user already granted the permissions
                        noPermissionsGranted = false;
                    }
                }

                // Add a listener to the Capture button
                Button captureButton = (Button) findViewById(R.id.button_capture);
                captureButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // get an image from the camera
                                if (!noPermissionsGranted || Build.VERSION.SDK_INT < 23) {
                                    mCamera.takePicture(null, null, mPicture);

                                }
                            }
                        }
                );

            }
        }catch (Exception e){

        }
    }

    /**
     * mirrors the image. Useful in cases that the application mirrors the image after the picture shot. This is common in
     * some manufactures, such as Motorola
     * @param bitmap - the picture
     * @return - the mirrored picture
     */
    public Bitmap mirror(Bitmap bitmap){
        //Camera pictured are mirrored. Use a matrix for correction.
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);

        //Orientation alters the appearance of the picture, which can be shown upside down or rotated
        //This line is used to correct the picture before saving it, according to the screen orientation
        Bitmap rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, false);

        return  rotatedImg;
    }

    /**
     * rotates the image to the orientation
     * @param bitmap - the image
     * @param orientation - the final desired orientation
     * @return
     */
    public Bitmap rotate(Bitmap bitmap, int orientation){
        //Camera pictured are mirrored. Use a matrix for correction.
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);

        //Orientation alters the appearance of the picture, which can be shown upside down or rotated
        //This line is used to correct the picture before saving it, according to the screen orientation
        Bitmap rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, false);

        return  rotatedImg;
    }

    /**
     * Creates a miniature image of the image
     * @param bitmap - the previous image
     * @param reduceProportion - the proportion to which the image will be reduced. For instance: 0,25 will reduce the image by 4x
     * @return
     */
    public Bitmap createMiniature(Bitmap bitmap, int reduceProportion){
        //Camera pictured are mirrored. Use a matrix for correction.
        Matrix matrix = new Matrix();
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth()*reduceProportion,bitmap.getHeight()*reduceProportion, matrix, false);
    }

    public Bitmap mirrorAndRotate(Bitmap bitmap, int orientation){
        //Camera pictured are mirrored. Use a matrix for correction.
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        matrix.postRotate(orientation);

        //Orientation alters the appearance of the picture, which can be shown upside down or rotated
        //This line is used to correct the picture before saving it, according to the screen orientation
        Bitmap rotatedImg = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, false);

        return  rotatedImg;
    }

    public void open(final int cameraId){
        try {
            this.mCamera = Camera.open(cameraId);
        }catch (Exception e){
            Log.d(TAG, "Camera is not supported in this device" + e.getMessage());
        }
    }

    private int findFrontFacingCameraID() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d("Info", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    public static int setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(CustomCameraActivity.this, CustomCameraActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),getString(R.string.msg_req_perm) , Toast.LENGTH_LONG).show();
        }

    }

}
