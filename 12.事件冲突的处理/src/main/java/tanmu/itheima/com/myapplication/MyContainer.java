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
    private final String TAG = "MyContainer";
    private float downX;
    private float downY;

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
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = e.getX();
                downY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = e.getX();
                float moveY = e.getY();
                float Xdis = Math.abs(downX - moveX);
                float Ydis = Math.abs(downY - moveY);
                if(Ydis<Xdis){
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: 父控件处理左右移动");
        return super.onTouchEvent(event);
    }
}
