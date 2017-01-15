package tanmu.itheima.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyViewGroup myViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewGroup = (MyViewGroup) findViewById(R.id.myViewGroup);
    }

    public void click(View v) {
        myViewGroup.change();
    }
}
