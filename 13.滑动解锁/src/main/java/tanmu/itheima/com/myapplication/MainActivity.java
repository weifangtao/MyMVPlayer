package tanmu.itheima.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView myView = (MyView) findViewById(R.id.slim);
        myView.setOnLockListener(new MyView.OnLockListener() {
            @Override
            public void onLock() {
                finish();
            }
        });
    }
}
