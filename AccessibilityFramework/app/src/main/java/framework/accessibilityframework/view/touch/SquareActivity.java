package framework.accessibilityframework.view.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.screen.PinchListener;

public class SquareActivity extends AppCompatActivity implements PinchListener.PinchCallback {

    private View smallSquare;
    private View bigSquare;
    private float tolerance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title3);

        tolerance = 20 * getResources().getDisplayMetrics().density;

        smallSquare = findViewById(R.id.small_square);
        bigSquare = findViewById(R.id.big_square);
        final ScaleGestureDetector detector = new ScaleGestureDetector(this, new PinchListener(smallSquare, this));

        smallSquare.getRootView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return false;
            }
        });
    }

    @Override
    public void onScale() {
        if (Math.abs(bigSquare.getMeasuredWidth() - smallSquare.getMeasuredWidth()*smallSquare.getScaleX()) < tolerance)
            smallSquare.setBackgroundColor(0xff00c133);
        else
            smallSquare.setBackgroundColor(0xff0063ce);
    }

    @Override
    public void onScaleEnd() {
        if (Math.abs(bigSquare.getMeasuredWidth() - smallSquare.getMeasuredWidth()*smallSquare.getScaleX()) < tolerance)
            finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
