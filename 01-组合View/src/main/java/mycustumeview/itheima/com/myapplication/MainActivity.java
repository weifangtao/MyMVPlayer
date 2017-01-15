package mycustumeview.itheima.com.myapplication;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

import mycustumeview.itheima.com.myapplication.adapter.PopAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopAdapter.OndeleteListener, AdapterView.OnItemClickListener {

    private ImageView imageView;
    private EditText edit;
    private ArrayList<String>datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
       // initView();
        //初始化数据
       // initData();
        //初始化事件
        //initEvent();
    }

    //初始化视图
    public void initView() {
        edit = (EditText) findViewById(R.id.editText);
        imageView = (ImageView) findViewById(R.id.arrow);
    }

    //初始化数据
    public void initData() {
        for (int i = 0; i < 100; i++) {
            datas.add("第"+i+"条");
        }
    }

    //初始化事件
    public void initEvent() {
        imageView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        showPopwindown();
    }

    private void showPopwindown() {
        PopupWindow popupWindow=new PopupWindow(this);
        //加载布局
        View view = View.inflate(this, R.layout.mypopwindown, null);
        //获取列表
        ListView listView = (ListView) view.findViewById(R.id.poplist);
        PopAdapter adapter=new PopAdapter(datas);
        listView.setAdapter(adapter);
        //设置删除的监听
        adapter.setOndeleteListener(this);
        //设置条目的点击事件
        popupWindow.setWidth(edit.getWidth());
        listView.setOnItemClickListener(this);
        popupWindow.setFocusable(true);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(view);
        //设置背景后才会有点击事件
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(edit,0,0);
    }

    @Override
    public void onDelete(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       edit.setText(datas.get(position));
    }
}
