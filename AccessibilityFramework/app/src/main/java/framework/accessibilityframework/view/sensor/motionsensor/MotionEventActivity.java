package framework.accessibilityframework.view.sensor.motionsensor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import framework.accessibilityframework.R;
import framework.accessibilityframework.control.SensorUtils;
import framework.accessibilityframework.control.sensor.LateralMovementListener;
import framework.accessibilityframework.control.sensor.SaltListener;
import framework.accessibilityframework.control.sensor.ShakeListener;

/**
 * Created by Olibario on 12/04/2018.
 */

public class MotionEventActivity extends AppCompatActivity{

    TextView orientationTV;
    TextView motionEventTV;
    TextView lateralMoveTV;
    TextView saltMoveTV;
    String orientation = "";
    String event = "";
    String lateralEvent = "";
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_sensor);

        orientationTV = findViewById(R.id.orientationValue);
        motionEventTV = findViewById(R.id.motionValue);
        lateralMoveTV = findViewById(R.id.lateralMoveValue);
        saltMoveTV = findViewById(R.id.saltValue);

        SensorUtils utils = new SensorUtils();

        int testaOrientacao = utils.getOrientation(this);

        if (testaOrientacao == 1){
            orientation = getString(R.string.portrait) +"\n";
        }
        else{
            orientation = getString(R.string.landscape);
        }

        testaOrientacao = utils.getSpecificOrientation(this);

        orientation = orientation +" "+ testaOrientacao;

        orientationTV.setText(orientation);

        ShakeListener shakeListener = new ShakeListener(this);
        shakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                event = getString(R.string.shakeMovement)+"\n";
                motionEventTV.setText(event);
            }

            @Override
            public void onLittleShake() {
                event = getString(R.string.littleShake)+"\n";
                motionEventTV.setText(event);
            }
        });

        LateralMovementListener lateralMovementListener = new LateralMovementListener(this);
        lateralMovementListener.setOnLateralMovementListener(new LateralMovementListener.OnLateralMoveListener() {
            @Override
            public void onLateralMovement() {
                lateralMoveTV.setText(getString(R.string.lateralMovements)+"\n");
            }

        });

        SaltListener saltListener = new SaltListener(this);
        saltListener.setOnSaltListener(new SaltListener.OnSaltListiner() {
            @Override
            public void onSalt() {
                saltMoveTV.setText(getString(R.string.saltMovements)+"\n");
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
