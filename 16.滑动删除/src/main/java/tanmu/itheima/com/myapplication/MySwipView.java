package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by 魏方涛 on 2017/1/4.
 */

public class MySwipView extends ViewGroup {

    private TextView button;
    private TextView leftText;
    private ViewDragHelper helper;
    private View leftChild;
    private View buttonChild;


    public MySwipView(Context context) {
        this(context, null);
    }

    public MySwipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = ViewDragHelper.create(this, callback);
    }

    private String TAG = "MySwipView";
    ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        /**
         * 是否可以拖拽
         * @param child
         * @param pointerId
         * @return
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            Log.d(TAG, "tryCaptureView: ");
            return true;
        }

        /**
         * 拖动在水平方向一块走
         * @param child   当前拖动的view
         * @param left    建议的left值
         * @param dx    x偏移量
         * @return 最终的left值
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //处理滑动越界问题
            if (child == leftChild) {
                if (left > 0) {
                    left = 0;
                } else if (left < -buttonChild.getMeasuredWidth()) {
                    left = -buttonChild.getMeasuredWidth();
                }
            } else if (child == buttonChild) {
                if (left < getMeasuredWidth() - buttonChild.getMeasuredWidth()) {
                    left = getMeasuredWidth() - buttonChild.getMeasuredWidth();
                }
            }
            return left;
        }

        /**
         * 拖动在数直方向一块走
         * @param child
         * @param top
         * @param dy
         * @return
         */
//        @Override
//        public int clampViewPositionVertical(View child, int top, int dy) {
//            return super.clampViewPositionVertical(child, top, dy);
//        }

        /**
         * View的位置改变的监听
         * @param changedView   改变位置的view
         * @param left   改变后的left值
         * @param top   改变后的top值
         * @param dx   x方向改变的距离  左为负
         * @param dy   y方向改变的距离  上为负
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
//            Log.d(TAG, "onViewPositionChanged: dx"+dx);
            if (leftChild == changedView) {
                //重新布局
//                invalidate();
                buttonChild.layout(buttonChild.getLeft() + dx, 0, buttonChild.getRight() + dx, buttonChild.getBottom());
            } else if (buttonChild == changedView) {
                leftChild.layout(leftChild.getLeft() + dx, 0, leftChild.getRight() + dx, leftChild.getBottom());
            }
        }

        /**
         * 手指松开时候的回调
         * @param releasedChild   从哪个View上松开
         * @param xvel
         * @param yvel
         */
        //处理动画效果,自动打开关闭
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //只需要关心删除按钮是否左边过半
            if (buttonChild.getLeft() > getMeasuredWidth() - buttonChild.getMeasuredWidth() / 2) {
                //关闭
                /**
                 * @param child Child view to capture and animate
                 * @param finalLeft Final left position of child
                 * @param finalTop Final top position of child
                 * @return true if animation should continue through {@link #continueSettling(boolean)} calls
                 */
                //只是做了位置计算
                helper.smoothSlideViewTo(buttonChild, getMeasuredWidth(), 0);
                invalidate();
            } else {
                //打开
                helper.smoothSlideViewTo(buttonChild, getMeasuredWidth() - buttonChild.getMeasuredWidth(), 0);
                invalidate();
            }
        }
    };

    @Override
    public void computeScroll() {
        super.computeScroll();
        /**
         * * @param deferCallbacks true if state callbacks should be deferred via posted message.
         *                       Set this to true if you are calling this method from
         *                       {@link android.view.View#computeScroll()} or similar methods
         *                       invoked as part of layout or drawing.
         * @return true if settle is still in progress
         */
        if(helper.continueSettling(true)){
//            invalidate();模拟器上使用不了
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量孩纸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取每一个孩纸
        leftChild = getChildAt(0);
        buttonChild = getChildAt(1);

        //布局孩纸
        leftChild.layout(0, 0, leftChild.getMeasuredWidth(), leftChild.getMeasuredHeight());
        buttonChild.layout(leftChild.getMeasuredWidth(), 0, leftChild.getMeasuredWidth() + buttonChild.getMeasuredWidth(), buttonChild.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //通过ViewDragHelper处理手势事件
        helper.
                processTouchEvent(event);
        return true;
    }
}
