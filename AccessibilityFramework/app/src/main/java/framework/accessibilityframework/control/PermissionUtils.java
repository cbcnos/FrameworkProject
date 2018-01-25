package framework.accessibilityframework.control;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * This is a helper class to make it easier for developers to manage
 * permissions of Android devices. This is specially important in cases of
 * devices with Android 6.0 and superior, because these ones required explicit
 * permission grant for each permission needed in the app, as they are required
 * at run time
 */
public class PermissionUtils extends AppCompatActivity {
    /**
     * Checks if a certain permission is granted or not by the user. This is necessary
     * if the device is equipped with Android 6.0 or superior. Check this by comparing
     * Build.Version.SDK_INT >= 23 (Android 6.0 API version)
     * @param context - the reference to the calling activity (screen)
     * @param permission - the name of the permission to be checked, exactly the one
     *                   that might be in the arquive AndroidManifest.xml.
     *                   Eg.: Manifest.permission.WRITE_EXTERNAL_STORAGE
     * @return - 0 if the permission is granted (same value as the constant PackageManager.GRANTED)
     *         - 1 if the permission is denied
     *         Notice that the method checkSelfPermission might return different parameters possibilities,
     *         which are NOT checked via this method
     */
    public static int checkPermission(Activity context, String permission){
        if (ContextCompat.checkSelfPermission(context,permission)== PackageManager.PERMISSION_GRANTED){
            return 0;
        }
        return 1;
    }

    /**
     * request a permission from the user at runtime. This is necessary
     * if the device is equipped with Android 6.0 or superior. Check this by comparing
     * Build.Version.SDK_INT >= 23 (Android 6.0 API version)
     * @param context - a reference to the activity
     * @param permissions - the name of the permissions to be checked, exactly the one
     *                   that might be in the arquive AndroidManifest.xml.
     *                   Eg.: Manifest.permission.WRITE_EXTERNAL_STORAGE
     * @param requestCode - the request code regarding this transaction, which is further used
     *                    in listener method onRequestPermissionsResult(), which has to be implemented
     *                    by calling class
     */
    public static void requestPermission(Activity context, String[] permissions, int requestCode){
        ActivityCompat.requestPermissions(context,permissions,requestCode);
    }
}
