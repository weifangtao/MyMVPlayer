package tanmu.itheima.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SlidMenu slidMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidMenu = (SlidMenu) findViewById(R.id.slidmenu);
    }
    public void click(View v){
        //强转
        TextView textView= (TextView) v;
        Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();
        slidMenu.close();
    }
}
