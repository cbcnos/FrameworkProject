package framework.accessibilityframework.view.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.screen.drapdrop.DragSourceListener;
import framework.accessibilityframework.control.screen.drapdrop.DropTargetListener;

public class FeedActivity extends AppCompatActivity implements DropTargetListener.DropCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title2);

        findViewById(R.id.feed_tomato).setOnTouchListener(new DragSourceListener());
        findViewById(R.id.feed_monster).setOnDragListener(new DropTargetListener(getApplicationContext(), this));
    }

    @Override
    public void onDropInside() {
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
