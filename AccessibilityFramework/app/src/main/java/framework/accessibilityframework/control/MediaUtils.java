package framework.accessibilityframework.control;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Olibario
 * This class handles functionalities regarding media files
 * (ie audio, image and video)
 */
public class MediaUtils {

    public static final int MEDIA_TYPE_AUDIO = 1;
    public static final int MEDIA_TYPE_IMAGE = 2;
    public static final int MEDIA_TYPE_VIDEO = 3;

    /**
     * Create a file Uri for saving an image or video
     * @param type - an integer describing the media type. See constants above
     *             for reference
     * @param dir - the name of the directory to save.
     * @param fileName - the filename, WITH its extension. If the extension is not
     *                 present, default extensions are added: .3gpp for audio,
     *                 .jpg for image and .mp4 for video
     * @return - the file uri
     */
    public static Uri getOutputMediaFileUri(int type, String dir, String fileName){
        return Uri.fromFile(getOutputMediaFile(type, dir, fileName));
    }

    /**
     * Create a File for saving a media
     * @param type - an integer describing the media type. See constants above
     *             for reference
     * @param directory - the name of the directory to save. Leave as null if yoy want MyApp default directory to be created
     * @param fileName - the filename, WITH its extension. If the extension is not
     *                 present, default extensions are added: .3gpp for audio,
     *                 .jpg for image and .mp4 for video
     * @return - the file
     */
    public static File getOutputMediaFile(int type, String directory, String fileName){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir;
        if ((directory!= null) && (!directory.equals(""))) {
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), directory);
        }
        else{
            mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES),"MyApp");
        }
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyApp", "failed to create directory");
                return null;
            }
        }

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            if (!fileName.contains(".") ) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "IMG_" + fileName + ".jpg");
            }
            else{ //we assume that the name of the image file contains its extension
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "IMG_" + fileName);
            }
        } else if(type == MEDIA_TYPE_VIDEO) {
            if (!fileName.contains(".")) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "VID_" + fileName + ".mp4");
            }
            else{ //we assume that the name of the video file contains its extension
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "VID_" + fileName);
            }
        } else {
            return null;
        }

        return mediaFile;
    }

    /**
     Creates a file, in the directory, in SD Card. This assumes that the user has already granted the permission
     to write to external storage directory
     @param part- the file name
     @param ext-  the file extension
     @param directory- the directory where the file will be stored. Consider using ".tmp" if you want the file to be temporary
     **/
    public static File createFile(String part, String ext, String directory) throws Exception
    {
        File tempDir= Environment.getExternalStorageDirectory();
        tempDir=new File(tempDir.getAbsolutePath()+"/"+directory+"/");
        if(!tempDir.exists())
        {
            tempDir.mkdirs();
        }
        if (ext.equals(".tmp")) {
            tempDir = File.createTempFile(part, ext, tempDir);
        }
        else{
            tempDir = new File(tempDir.getAbsolutePath()+"/"+part+ext);
        }
        return tempDir;
    }
}
