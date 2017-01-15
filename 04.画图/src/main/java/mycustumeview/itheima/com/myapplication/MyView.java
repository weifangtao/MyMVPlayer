package mycustumeview.itheima.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 魏方涛 on 2017/1/2.
 */

public class MyView extends View {

    private Paint paint;
    private Path path;
    private RectF oval;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        //  画空心圆
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        //路径
//        path = new Path();
//        path.moveTo(0,0);
//        path.lineTo(100,100);
//        path.lineTo(400,150);
//        path.lineTo(0,0);
        //画扇形
        oval = new RectF();
        oval.set(0,0,200,200);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画直线
//        float startX = 100;
//        float startY = 100;
//        float endX = 400;
//        float endY = 400;
//        canvas.drawLine(startX, startY, endX, endY, paint);
        // 画圆
//        float x=200;
//        float y=200;
//        float radius=100;
//        canvas.drawCircle(x,y,radius,paint);
        // 裁剪
//        canvas.clipPath(path);
//        //画图片
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.haha);
//        canvas.drawBitmap(bitmap,0,0,paint);
        //画三角形
//        canvas.drawPath(path,paint);

        // 画扇形

        canvas.drawArc(oval,0,60,false,paint);//true 代表包含两条边界,false代表不包含边界
    }
}
