package framework.accessibilityframework.view.touch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.screen.TouchListener;

public class TouchActivity extends AppCompatActivity implements TouchListener.TouchCallback {

    private RelativeLayout layout;
    private float density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title4);

        layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_touch, null);
        setContentView(layout);

        density = getResources().getDisplayMetrics().density;
        layout.setOnTouchListener(new TouchListener(this));
    }

    @Override
    public void onTouch(int index, float x, float y) {
        final TextView number = (TextView) getLayoutInflater().inflate(R.layout.activity_touch_number, null);
        number.setText(String.valueOf(index+1));
        number.setX(x-30*density);
        number.setY(y-30*density);
        number.animate().alpha(0).setStartDelay(1500).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                layout.removeView(number);
            }
        }).start();
        layout.addView(number, (int)(60*density), (int)(60*density));
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
