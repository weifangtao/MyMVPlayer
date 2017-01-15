package mycustumeview.itheima.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyView.OnclickIsOpen {

    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = (MyView) findViewById(R.id.slid);
        myView.setOnclickIsOpen(this);

    }

    @Override
    public void isOpen(boolean isopen) {
        if(isopen){
            Toast.makeText(this,"打开",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"关闭",Toast.LENGTH_LONG).show();
        }
    }
}
