package tanmu.itheima.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> datas = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();
    }

    //初始化视图
    public void initView() {
        listView = (ListView) findViewById(R.id.listview);
    }

    //初始化数据
    public void initData() {
        for (int i = 0; i < 50; i++) {
            datas.add("第"+i+"条");
        }
    }

    //初始化事件
    public void initEvent() {
        SwipAdapter adapter=new SwipAdapter(datas);
        listView.setAdapter(adapter);
    }

}
