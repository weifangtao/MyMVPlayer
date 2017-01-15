package tanmu.itheima.com.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 魏方涛 on 2017/1/5.
 */

public class MyClockView extends View {

    private float radius;
    private float cy;
    private float cx;
    private Paint paint;
    private int stroke_width;
    private int min_degree_width;
    private int hour_degree_width;
    private float top_width;
    private float textSize;
    private Paint textPaint;
    private Bitmap logo;
    private int logo_top;
    private int hour_arrow;
    private int min_arrow;
    private int sec_arrow;
    private int sec_arrow_width;
    private int min_arrow_width;
    private int hour_arrow_width;
    private int clock_bottom;
    private Paint arrowPaint;
    private Paint backPaint;
    private int hourRotate;
    private int minRotate;
    private int secRotate;
    private float finalTextSize;


    public MyClockView(Context context) {
        this(context, null);
    }

    public MyClockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //加载图片
        logo = BitmapFactory.decodeResource(getResources(), R.drawable.heima);
        //logo图片距离
        logo_top = getResources().getDimensionPixelOffset(R.dimen.logo_top);
        //描边的宽度
        stroke_width = getResources().getDimensionPixelOffset(R.dimen.stroke_width);
        //刻度的长度   时,分
        hour_degree_width = getResources().getDimensionPixelOffset(R.dimen.hour_degree_width);
        min_degree_width = getResources().getDimensionPixelOffset(R.dimen.min_degree_width);
        //文字距离时刻度的距离
        top_width = getResources().getDimensionPixelSize(R.dimen.top_width);
        //文字的大小
        textSize = getResources().getDimension(R.dimen.text_size);
        //指针的长度 时针 分针  秒针   距离时刻度距离
        hour_arrow = getResources().getDimensionPixelOffset(R.dimen.hour_arrow);
        min_arrow = getResources().getDimensionPixelOffset(R.dimen.min_arrow);
        sec_arrow = getResources().getDimensionPixelOffset(R.dimen.sec_arrow);
        //指针的宽度  时针 分针  秒针
        hour_arrow_width = getResources().getDimensionPixelOffset(R.dimen.hour_arrow_width);
        min_arrow_width = getResources().getDimensionPixelOffset(R.dimen.min_arrow_width);
        sec_arrow_width = getResources().getDimensionPixelOffset(R.dimen.sec_arrow_width);
        //时针背面长度
        clock_bottom = getResources().getDimensionPixelOffset(R.dimen.clock_bottom);

        //创建画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke_width);
        //画文字的画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画指针的画笔
        arrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画背景的画笔
        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //设置文字居中显示
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);
        //字体测量
        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        //正
        float descent = fontMetrics.descent;
        //负值
        float ascent = fontMetrics.ascent;
        finalTextSize = textSize / 2 + (descent - ascent) / 2 - ascent;
        //获取当前时间 绘制时针 分针以及秒针需要旋转的角度
        initArrowRotateDegree();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //6.画背景
        drawBacground(canvas);
        //1.画圆
        drawCircle(canvas);
        //2.画刻度
        drawDegree(canvas);
        //3.画数字
        drawNumber(canvas);
        //4.画图片
        drawLogo(canvas);
        //5.画指针
        drawArrow(canvas);
        //7.指针转动
        startTick();
    }

    //指针转动
    private void startTick() {
        postDelayed(runnable, 1000);
    }

    Runnable runnable = new Runnable() {

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void run() {
            //获取当前时间 绘制时针 分针以及秒针需要旋转的角度
            initArrowRotateDegree();
            invalidate();
            //递归调用
            startTick();
        }

    };

    //获取当前时间 绘制时针 分针以及秒针需要旋转的角度
    @TargetApi(Build.VERSION_CODES.N)
    private void initArrowRotateDegree() {
        Calendar calender = Calendar.getInstance();
        calender.setTimeInMillis(System.currentTimeMillis());
        //获取时间   12小时制
        int hour = calender.get(Calendar.HOUR);
        int min = calender.get(Calendar.MINUTE);
        int sec = calender.get(Calendar.SECOND);
        //将时间转化为角度
        hourRotate = 30 * hour;
        minRotate = 6 * min;
        secRotate = 6 * sec;
    }

    //画背景
    private void drawBacground(Canvas canvas) {
        backPaint.setColor(Color.parseColor("#33000000"));
        canvas.drawCircle(cx, cy, radius, backPaint);
    }

    //5.画指针
    private void drawArrow(Canvas canvas) {
        //时针
        drawHoursArrow(canvas);
        //分针
        drawMinArrow(canvas);
        //秒针
        drawSecArrow(canvas);
    }

    //分针
    private void drawMinArrow(Canvas canvas) {
        Path path = new Path();
        float x1 = cx, y1 = stroke_width + hour_degree_width + min_arrow;
        float x2 = cx + min_arrow_width / 2, y2 = cy + clock_bottom;
        float x3 = cx, y3 = cy;
        float x4 = cx - min_arrow_width / 2, y4 = cy + clock_bottom;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.lineTo(x1, y1);
        canvas.rotate(minRotate, cx, cy);
        arrowPaint.setColor(Color.BLUE);
        canvas.drawPath(path, arrowPaint);
        canvas.rotate(-minRotate, cx, cy);
    }

    //秒针
    private void drawSecArrow(Canvas canvas) {
        Path path = new Path();
        float x1 = cx, y1 = stroke_width + hour_degree_width + sec_arrow;
        float x2 = cx + sec_arrow_width / 2, y2 = cy + clock_bottom;
        float x3 = cx, y3 = cy;
        float x4 = cx - sec_arrow_width / 2, y4 = cy + clock_bottom;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.lineTo(x1, y1);
        canvas.rotate(secRotate, cx, cy);
        arrowPaint.setColor(Color.YELLOW);
        canvas.drawPath(path, arrowPaint);
        canvas.rotate(-secRotate, cx, cy);
    }


    //时针
    private void drawHoursArrow(Canvas canvas) {
        Path path = new Path();
        float x1 = cx, y1 = stroke_width + hour_degree_width + hour_arrow;
        float x2 = cx + hour_arrow_width / 2, y2 = cy + clock_bottom;
        float x3 = cx, y3 = cy;
        float x4 = cx - hour_arrow_width / 2, y4 = cy + clock_bottom;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x4, y4);
        path.lineTo(x1, y1);
        arrowPaint.setColor(Color.RED);
        canvas.rotate(hourRotate, cx, cy);
        canvas.drawPath(path, arrowPaint);
        //复位
        canvas.rotate(-hourRotate, cx, cy);
    }

    //4.画图片
    private void drawLogo(Canvas canvas) {
        canvas.drawBitmap(logo, cx - logo.getWidth() / 2, stroke_width + hour_degree_width + textSize + logo_top, paint);
    }

    //3.画数字
    private void drawNumber(Canvas canvas) {
//        canvas.drawText("12",cx,stroke_width+hour_degree_width+top_width+textSize,textPaint);
        for (int i = 1; i < 13; i++) {
            //时钟旋转
            canvas.rotate(30, cx, cy);
            //把文字旋转

            canvas.rotate(-30 * i, cx, stroke_width + hour_degree_width + textSize / 2);
            canvas.drawText(i + "", cx, stroke_width + hour_degree_width + finalTextSize, textPaint);
            canvas.rotate(30 * i, cx, stroke_width + hour_degree_width + textSize / 2);
        }
    }

    //画刻度
    private void drawDegree(Canvas canvas) {
//        画小时
//        canvas.drawLine(cx,stroke_width,cx,stroke_width+hour_degree_width,paint);
        for (int i = 0; i < 60; i++) {
            if (i % 5 == 0) {
                //画小时刻度
                canvas.drawLine(cx, stroke_width, cx, stroke_width + hour_degree_width, paint);
            } else {
                //画分钟的刻度
                canvas.drawLine(cx, stroke_width, cx, stroke_width + min_degree_width, paint);
            }
            //画完之后旋转
            canvas.rotate(6, cx, cy);
        }
    }

    //画圆
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(cx, cy, radius, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx = w / 2;
        cy = h / 2;
        radius = cx - stroke_width;
        int[] colors = {Color.parseColor("#99000000"), Color.parseColor("#33000000"), Color.parseColor("#ffffff")};
        float[] stops = {0f, 0.5f, 1.0f};
        /**
         * TileMode.CLAMP   直接用最外层的颜色填充
         * TileMode.REPEAT  重复按照顺序填充
         * TileMode.MIRROR  镜像填充
         */
        Shader shader = new RadialGradient(cx, cy, radius / 2, colors, stops, Shader.TileMode.MIRROR);
        backPaint.setShader(shader);
    }

    //从窗体移除
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //从消息队列中移除
        removeCallbacks(runnable);
    }

    //添加到窗体
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTick();
    }
}
