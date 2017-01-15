package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 魏方涛 on 2017/1/3.
 */

public class MyView extends View {
    private static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        boolean dispatchTouchEvent=true;
//        Log.d(TAG, "dispatchTouchEvent: "+dispatchTouchEvent);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchEvent=true;
        if(onTouchEvent){
            Log.d(TAG, "消费");
        }else {
            Log.d(TAG, "不消费");
        }
        return onTouchEvent;
    }
}
