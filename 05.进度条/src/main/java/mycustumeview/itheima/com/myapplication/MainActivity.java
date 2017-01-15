package mycustumeview.itheima.com.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private MyView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (MyView) findViewById(R.id.progress);
    }
    public void click(View v){
       new Thread(){
           @Override
           public void run() {
               super.run();
        for (int i = 0; i < 100; i++) {
            SystemClock.sleep(50);
            progressBar.setPath(i);
        }
           }
       }.start();
    }
}
