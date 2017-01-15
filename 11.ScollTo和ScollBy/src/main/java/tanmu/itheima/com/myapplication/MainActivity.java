package tanmu.itheima.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //只移动里面的内容 容器不会改变
//               textView.scrollTo(50,0);//相对于初始位置移动多少  左,上为正
                textView.scrollBy(100, 0);//相对于上一次位置移动多少,左 上为正
            }
        });
    }
}
