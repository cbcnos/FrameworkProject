package framework.accessibilityframework.control.screen.drapdrop;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * This class is meant to be used in components to be dragged to a target. It
 * may or may not be drooped at the target's adress.
 * In order to use it, do something like
 * component.setOnTouchListener(new DragSourceListener());
 */

public class DragSourceListener implements View.OnTouchListener {
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");

            //makes a shadow of the view
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    view);

            //starts the dragging movement. If you have another component to which
            //the source will be dropped, then implement what you want to do with this source
            // ate your target
            view.startDrag(data, shadowBuilder, view, 0);

            //making the source invisible while dragging. You might want to change this
            view.setVisibility(View.INVISIBLE);
            return true;
        }
        else{
            return false;
        }
    }
}
