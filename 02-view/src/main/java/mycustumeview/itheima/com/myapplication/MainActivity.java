package mycustumeview.itheima.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        RelativeLayout relativeLayout=new RelativeLayout(this);
        TextView textView=new TextView(this);
        textView.setText("hello");
        relativeLayout.addView(textView);
        setContentView(relativeLayout);
    }
}
