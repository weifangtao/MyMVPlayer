package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 魏方涛 on 2017/1/3.
 */

public class MyViewGroup extends ViewGroup {
    private boolean isOragnal = true;

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        View childAt = getChildAt(0);
//        childAt.measure();
        //手动测量所有的孩纸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获得孩纸的个数
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //获取孩纸的宽高
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int left = 0;
            int top = 0;
            int right = 0;
            int bottom = 0;
            //left
            if (isOragnal) {
                if (i % 2 == 0) {
                    left = 0;
                } else {
                    left = getMeasuredWidth() - measuredWidth;
                }
            } else {
                if (i % 2 == 0) {
                    left = getMeasuredWidth() - measuredWidth;
                } else {
                    left = 0;

                }
            }
            //top
            top = measuredHeight * i;
            //right
            right = left + measuredWidth;
            //bottom
            bottom = top + measuredHeight;
            childAt.layout(left, top, right, bottom);
        }
    }

    public void change() {
        isOragnal = !isOragnal;
//        invalidate();
        requestLayout();
    }

}
