package tanmu.itheima.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * created by 魏方涛
 */

public class MyView extends View {

    private int    widthButton;
    private Bitmap bitmap;
    private int    heightButton;

    private Paint          paint;
    private float          downX;
    private Scroller       scroller;
    private int            viewW;
    private OnLockListener listener;


    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //加载图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);
        widthButton = bitmap.getWidth();
        heightButton = bitmap.getHeight();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        scroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightButton);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                float offsetX = -(downX - widthButton / 2);
                if (downX > widthButton) {
                    return false;
                }
                if (downX > widthButton / 2) {
                    scrollTo((int) offsetX, 0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float scrollX = -(moveX - widthButton / 2);
                if (moveX < widthButton / 2) {
                    scrollX = 0;
                } else if (moveX > viewW - widthButton / 2) {
                    scrollX = -(viewW - widthButton);
                }
                //开始滑动,只是计算了各个时间点要滑动到的位置
                scrollTo((int) scrollX, 0);
                break;
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                float endX = -(upX - downX);
                if (upX < viewW - widthButton / 2) {
                    //需要增加动画效果
                    //scrollTo(0,0);
                    int startX = getScrollX();//获取x轴的偏移量
                    int startY = getScrollY();//获取y轴的偏移量
                    int dx = -startX;//需要移动的距离
                    int dy = -startY;
                    scroller.startScroll(startX, startY, dx, dy);
                    //重新绘制
                    invalidate();
                } else {
                    //需要在activity中销毁界面
                    if (listener != null) {
                        listener.onLock();
                    }
                }
                break;
        }
        //消费掉事件
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断当前还需不需要滑动  true需要  false 不需要
        if (scroller.computeScrollOffset()) {
            //scroller.getCurrX()当前需要滑动到的位置偏移量
            scrollTo(scroller.getCurrX(), 0);
//            invalidate();
//            invalidate();在有些模拟器上的兼容
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewW = w;
    }

    public interface OnLockListener {
        void onLock();
    }

    public void setOnLockListener(OnLockListener listener) {
        this.listener = listener;
    }
}
