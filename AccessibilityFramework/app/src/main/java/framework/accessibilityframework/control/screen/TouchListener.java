package framework.accessibilityframework.control.screen;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * This class identifies both single touch and multitouch events made by the user to a view or activity.
 * To use it on a component, simply add this listener to the component (view / activity) and
 * change its methods according to your needs. Something like this:
    TouchListener touchListener = new TouchListener();
    view.setOnTouchListener(new TouchListener());
 */

public class TouchListener implements View.OnTouchListener {

    /**
     * Use this listener to identify whether the user clicked or touched on the view with one or more fingers.
     * In case of multitouch (more than one finger), the fisrt finger to touch the view has index 0 and the additional
     * fingers have greater indexes. This is useful for simple touch gestures.
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int index = motionEvent.getActionIndex();
        int numberOfFingers = motionEvent.getPointerCount(); //number of fingers to touch the view
        float xPos = -1, yPos = -1;
        boolean singleTouch = true;

        if (numberOfFingers > 1) {
            Log.d("DEBUG","Multitouch event");
            // The coordinates of the current screen contact, relative to
            // the responding View or Activity.
            xPos = motionEvent.getX(index);
            yPos = motionEvent.getY(index);
            singleTouch = false;
        } else {
            // Single touch event
            Log.d("DEBUG","Single touch event");
            xPos = motionEvent.getX(index);
            yPos = motionEvent.getY(index);
        }

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (singleTouch){
                    //user pressed the view with only one finger
                    Log.d("DEBUG","Single touch action DOWN event");
                }
                else{
                    //first finger of the user pressed the view
                    Log.d("DEBUG","First finger touched the element");
                }
                break;
            case MotionEvent.ACTION_UP:
                if (singleTouch){
                    //user single touch event released the view
                    Log.d("DEBUG","Single touch action DOWN event");
                }
                else{
                    //first finger of multitouch event released the view
                    Log.d("DEBUG","First finger of multitouh event released the element");
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //one of the fingers (that are not the first finger) touched the view
                Log.d("DEBUG","Finger number "+ index +" (of a total of "+numberOfFingers+") touched the view");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //one of the fingers (that are not the first finger) released the view
                Log.d("DEBUG","Finger number "+ index +" (of a total of "+numberOfFingers+") relased the view");
                break;
            case MotionEvent.ACTION_MOVE:
                if (singleTouch){
                    //user single touch event released the view
                    Log.d("DEBUG","Single touch action DOWN event");
                }
                else{
                    //first finger of multitouch event released the view
                    Log.d("DEBUG","Finger number "+ index +" (of a total of "+numberOfFingers+") moved the view");
                }
                break;
        }
        return false;
    }
}
