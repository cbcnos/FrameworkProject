package framework.accessibilityframework.control.screen;

import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * This class is useful for identifying pinch gestures. In order to use it on a certain view or activity,
 * you have to create an instance of this class and add a listener to your view/ activity. Somthing like this:
    ScaleGestureDetector mScaleDetector =
    new ScaleGestureDetector(this, new PinchListener(view));
 *
 * After that, you'll have to implement the ontouch() method, like thiS:
 *
     b.setOnTouchListener(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mScaleDetector.onTouchEvent(event);
            return false;
        }
     });
 * By the time the user touches the view, the method onScale will be called if the touch gesture is followed
 * by a pinch gesture.
 */

public class PinchListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
    /** the protected view bellow can be used as the view that will receive teh user's gesture. In order
     * to use it in a view of another class, just set the view you want to the constructor method, like shown on the header
     * of this class (above)
     */
    public View view;

    PinchListener(View v){
        this.view = v;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {

        float scaleFactor = detector.getScaleFactor(); //this is the scale factor of the pinch gesture
        //If you desire to resize the view when the user zooms in / out, please refer to the method scaleView()

        if (scaleFactor > 1) {
            //user is zooming out (fingers getting closer)
            Log.d("DEBUG","Zooming out");
        } else {
            //user is zooming in (fingers getting away, in opposite directions)
            Log.d("DEBUG","Zooming in");
        }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    /**
     * Method for resizing a view. Useful for example for zooming in/ out objects
     * @param scaleFactor
     */
    public void scaleView(float scaleFactor){
        scaleFactor = (scaleFactor < 1 ? 1 : scaleFactor); // prevent our view from becoming too small
        scaleFactor = ((float)((int)(scaleFactor * 100))) / 100; // Change precision to help with jitter when user just rests their fingers

        //resizing the view
        view.setScaleX(scaleFactor);
        view.setScaleY(scaleFactor);
    }
}
