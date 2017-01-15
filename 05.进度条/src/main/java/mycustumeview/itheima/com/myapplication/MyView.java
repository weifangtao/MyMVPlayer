package mycustumeview.itheima.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 魏方涛 on 2017/1/2.
 */

public class MyView extends View {

    private Paint paint;
    private RectF oval;
    private float path;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        oval = new RectF();
        oval.set(0, 0, 200, 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(oval, 0, path, false, paint);
    }

    public void setPath(int progress) {

        path = (progress/(float)100)*360;
        //invalidate();
        postInvalidate();
    }
}
