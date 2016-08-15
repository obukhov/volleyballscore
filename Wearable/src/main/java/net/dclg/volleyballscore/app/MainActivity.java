package net.dclg.volleyballscore.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.wearable.view.DismissOverlayView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private GestureDetectorCompat mGestureDetector;
    private TextView mWeTextView;
    private TextView mTheyTextView;
    private DismissOverlayView mDismissOverlayView;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.main_activity);

        mDismissOverlayView = (DismissOverlayView) findViewById(R.id.dismiss_overlay);
        mDismissOverlayView.setIntroText(R.string.intro_text);
        mDismissOverlayView.showIntroIfNecessary();

        mWeTextView = (TextView) findViewById(R.id.we);
        mTheyTextView = (TextView) findViewById(R.id.they);

        mGestureDetector = new GestureDetectorCompat(this, new LongPressListener());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event) || super.dispatchTouchEvent(event);
    }

    private class LongPressListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent event) {
            mDismissOverlayView.show();
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (velocityX < -2000 && velocityY < 1200 && velocityY > -1200) {
                String val = mWeTextView.getText().toString();
                Integer we = Integer.parseInt(val);
                we++;

                mWeTextView.setText(we.toString());
            }

            if (velocityX > 2000 && velocityY < 1200 && velocityY > -1200) {
                String val = mTheyTextView.getText().toString();
                Integer we = Integer.parseInt(val);
                we++;

                mTheyTextView.setText(we.toString());
            }

            if (velocityY > 3000 && velocityX < 1200 && velocityX > -1200) {
                mWeTextView.setText("0");
                mTheyTextView.setText("0");
            }

            return true;
        }
    }


    /**
     * Handles the button press to finish this activity and take the user back to the Home.
     */
    public void onFinishActivity(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
