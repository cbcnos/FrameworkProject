package framework.accessibilityframework.view.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.Random;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.screen.TouchListener;

public class TomatoActivity extends AppCompatActivity implements TouchListener.TouchCallback {

    private ImageView tomato;
    private Random random;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.title1);

        counter = 5;
        random = new Random();
        tomato = findViewById(R.id.tomato_image);
        tomato.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tomato.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                tomato.setOnTouchListener(new TouchListener(TomatoActivity.this));
            }
        });
    }

    private void tomatoRandomPosition() {
        tomato.setX(random.nextInt(findViewById(R.id.tomato_container).getMeasuredWidth() - tomato.getMeasuredWidth()));
        tomato.setY(random.nextInt(findViewById(R.id.tomato_container).getMeasuredHeight() - tomato.getMeasuredHeight()));
    }

    @Override
    public void onTouch(int index, float x, float y) {
        if (--counter == 0)
            finish();
        else
            tomatoRandomPosition();
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
