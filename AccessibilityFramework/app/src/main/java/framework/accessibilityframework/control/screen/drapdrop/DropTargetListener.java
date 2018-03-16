package framework.accessibilityframework.control.screen.drapdrop;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;

import framework.accessibilityframework.R;

/**
 * This class is meant to be used with a component to which you want to drag
 * (and/ or drop) other components. This is the target of the drag-and-drop.
 * In order to use this, you might add a touch listener to your source and
 * add this DragTargetListener to your target. You may pass the context of
 * your application (the activity or fragment, for instance)
 * to this listener in order to use the shading behaviours of
 * the shape xml files of drawable folder. You should do something like this
 * at your target component (view):
 *
 * component.setOnDragListener(new DropTargetListener(getApplicationContext()))
 */
class DropTargetListener implements View.OnDragListener {

    private Context context;

    DropTargetListener(Context context){
        this.context = context;
    }

    //flag that indicates if the source was dropped at the target's place
    boolean sourceDroppedInTarget = false;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        View sourceView = (View) event.getLocalState(); //the view that is dragged (source)

        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // user wants to drag the source
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                //this indicates to a View (target) that the drag point
                // has entered the bounding box of the View.
                v.setBackgroundResource(R.drawable.shape_drop_target);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                //indicates that the user has moved the drag shadow out of the
                // bounding box of the View or into a descendant view
                // that can accept the data.
                v.setBackgroundResource(R.drawable.shape);
                break;
            case DragEvent.ACTION_DROP:
                //user has released the drag shadow (dropped)
                sourceDroppedInTarget = true;
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                v.setBackgroundResource(R.drawable.shape);

                //if the source was released outside the target, it may become visible again
                if (!sourceDroppedInTarget) {
                    sourceView.setVisibility(View.VISIBLE);
                }
            default:
                break;
        }
        return true;
    }
}
