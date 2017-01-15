package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by 魏方涛 on 2017/1/3.
 */

public class MyContainer extends RelativeLayout {
    private String TAG = "MyContainer";

    public MyContainer(Context context) {
        super(context);
    }

    public MyContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        boolean dispatchTouchEvent = true;
//        Log.d(TAG, "dispatchTouchEvent: " + dispatchTouchEvent);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean onInterceptTouchEvent = false;
        if(onInterceptTouchEvent){
            Log.d(TAG, "父容器拦截");
        }else {
            Log.d(TAG, "父容器不拦截");

        }
        return onInterceptTouchEvent;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchEvent = false;
        if(onTouchEvent){
            Log.d(TAG, "消费");
        }else {
            Log.d(TAG, "不消费");
        }
        return onTouchEvent;
    }
}
