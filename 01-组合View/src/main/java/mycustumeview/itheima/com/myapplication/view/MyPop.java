package mycustumeview.itheima.com.myapplication.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import mycustumeview.itheima.com.myapplication.R;
import mycustumeview.itheima.com.myapplication.adapter.PopAdapter;

/**
 * Created by 魏方涛 on 2017/1/1.
 */

public class MyPop extends RelativeLayout implements View.OnClickListener, AdapterView.OnItemClickListener, PopAdapter.OndeleteListener {
    private final Context context;
    private EditText edit;
    private ImageView status;
    private ArrayList<String> datas = new ArrayList<>();

    public MyPop(Context context) {
        this(context, null);
    }

    public MyPop(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyPop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View.inflate(context, R.layout.mypop_view, this);


        //初始化视图
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();
    }

    //初始化视图
    public void initView() {
        edit = (EditText) findViewById(R.id.editText);
        status = (ImageView) findViewById(R.id.arrow);
    }

    //初始化数据
    public void initData() {
        for (int i = 0; i < 100; i++) {
            datas.add("第"+i+"条");
        }
    }

    //初始化事件
    public void initEvent() {

        status.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        showPopwindow();
    }

    private void showPopwindow() {
        PopupWindow popupWindow = new PopupWindow(this);
        View popview = View.inflate(context, R.layout.mypopwindown, null);
        ListView lv = (ListView) popview.findViewById(R.id.poplist);
        PopAdapter adapter=new PopAdapter(datas);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        adapter.setOndeleteListener(this);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setWidth(edit.getWidth());
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setContentView(popview);
        popupWindow.showAsDropDown(edit, 0, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        edit.setText(datas.get(position));
    }

    @Override
    public void onDelete(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
