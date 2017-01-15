package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by 魏方涛 on 2017/1/3.
 */

public class SlidMenu extends ViewGroup {

    private float downX;
    private Scroller scroller;
    private View leftChild;
    private View containerChild;
    public boolean isopen = false;


    public SlidMenu(Context context) {
        this(context, null);
    }

    public SlidMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量每个孩纸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获得每个孩纸
        leftChild = getChildAt(0);
        containerChild = getChildAt(1);
        //布局孩纸
        int childLeft = -leftChild.getMeasuredWidth();
        int childRight = 0;
        int childTop = 0;
        int childBottom = leftChild.getMeasuredHeight();
        leftChild.layout(childLeft, childTop, childRight, childBottom);

        int containerLeft = 0;
        int containerRight = containerChild.getMeasuredWidth();
        int containerTop = 0;
        int containerBottom = containerChild.getMeasuredHeight();
        containerChild.layout(containerLeft, containerTop, containerRight, containerBottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                //获取已经偏移量
                int scrollX = getScrollX();
                //将要偏移量
                float offsetX = downX - moveX;
                float finalX = scrollX + offsetX;
                //边界处理
//                if (finalX <-leftChild.getMeasuredWidth()) {
////                    finalX = -leftChild.getMeasuredWidth();
//                    scrollTo(-leftChild.getWidth(), 0);
//                    return true;
//                } else if (finalX > 0) {
////                    finalX = 0;
//                    scrollTo(0, 0);
//                    return true;
//                }
                if (finalX < -leftChild.getMeasuredWidth()) {
                    scrollTo(-leftChild.getMeasuredWidth(), 0);
                    return true;
                } else if (finalX > 0) {
                    scrollTo(0, 0);
                    return true;

                }

                scrollBy((int) offsetX, 0);
                downX = moveX;
                break;

            case MotionEvent.ACTION_UP:
                float upScrollX = getScrollX();
                if (upScrollX < -leftChild.getMeasuredWidth() / 2) {
                    //打开
                    open();
//                    scrollTo(-leftChild.getMeasuredWidth(), 0);

                } else {
                    //关闭
//                    close();
                    scrollTo(0, 0);

                }
                break;
        }
        return true;
    }

    public void close() {
        int startX = getScrollX();
        int startY = 0;
        int dx = -startX;
        int dy = 0;
        scroller.startScroll(startX, startY, dx, dy);
        invalidate();
//        scrollTo(0,0);
//        ViewCompat.postInvalidateOnAnimation(this);
        isopen = false;
    }

    private void open() {
        int startX = getScrollX();
        int startY = 0;
        int dx = -(leftChild.getMeasuredWidth() + startX);
        int dy = 0;
        scroller.startScroll(startX, startY, dx, dy);
        invalidate();
//        ViewCompat.postInvalidateOnAnimation(this);
        isopen = true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
//            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //布局解析完成之后
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(R.id.back);
        imageView.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                if (isopen) {
                    //关闭
                    close();
                } else {
                    //打开
                    open();
                }
            }
        });
    }
}
