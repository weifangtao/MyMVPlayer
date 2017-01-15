package mycustumeview.itheima.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 魏方涛 on 2017/1/2.
 */

public class MyView extends View{

    private Bitmap backaground;
    private Bitmap button;
    private Paint paint;
    private boolean isopen=false;
    private MyView.OnclickIsOpen listener;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backaground = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        button = BitmapFactory.decodeResource(getResources(), R.drawable.switch_button);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isopen=!isopen;
                invalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = backaground.getWidth();
        int height = backaground.getHeight();
        setMeasuredDimension(width,height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backaground,0,0,paint);
        if(!isopen){
            canvas.drawBitmap(button,0,0,paint);
            listener.isOpen(isopen);
        }else{
            canvas.drawBitmap(button,backaground.getWidth()-button.getWidth(),0,paint);
            listener.isOpen(isopen);
        }
    }
    public interface OnclickIsOpen{

        void isOpen(boolean isopen);
    }
    public void setOnclickIsOpen(OnclickIsOpen listener){

        this.listener = listener;
    }
}
