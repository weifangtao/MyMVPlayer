package tanmu.itheima.com.myapplication;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity";
    private ViewPager viewPager;
    private TextView title;
    //图片数组
    private int[] imageViews = {R.drawable.icon_1, R.drawable.icon_2,
            R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5,};
    private LinearLayout dot_container;
    //标题数组
    private String[] titles = {"为梦想坚持", "我相信我是黑马", "黑马公开课", "google开发者大会", "辅导班"};
    //选中小圆圈图标的选中状态  默认第一个被选中
    private int markPostion = 0;

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
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        dot_container = (LinearLayout) findViewById(R.id.dot_container);
        title = (TextView) findViewById(R.id.title);
    }

    //初始化数据
    public void initData() {
        title.setText(titles[0]);
        int dot_size = getResources().getDimensionPixelOffset(R.dimen.dot_size);
        for (int i = 0; i < imageViews.length; i++) {
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dot_size, dot_size);
            params.leftMargin = 2 * dot_size;
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_selector);
            if (i == 0) {
                view.setSelected(true);
            }
            //添加到容器中去
            dot_container.addView(view);
        }
    }

    //初始化事件
    public void initEvent() {
        viewPager.setAdapter(adapter);
        //设置条目为中间值
        viewPager.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViews.length);
//        Log.d(TAG, "initEvent: "+(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%imageViews.length));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            /**
             * 页面滚动
             * @param position   滚动之前的位置
             * @param positionOffset   滚动距离和Viewpager宽度的百分比
             * @param positionOffsetPixels   滚动距离的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d(TAG, "positionOffset="+positionOffset+"positionOffsetPixels="+positionOffsetPixels);
            }

            /**
             * 页面选中状态
             * @param position   页面选中的位置
             */
            @Override
            public void onPageSelected(int position) {
                //防止索引越界
                position = position % imageViews.length;
//                Log.d(TAG, "onPageSelected: "+position);
                title.setText(titles[position]);
                Log.d(TAG, "onPageSelected: "+position);
                dot_container.getChildAt(markPostion).setSelected(false);
                dot_container.getChildAt(position).setSelected(true);
                //重新赋值
                markPostion = position;
            }

            /**
             * 页面状态变化的时候
             * @param state
             * @see ViewPager#SCROLL_STATE_IDLE    空闲状态
             * @see ViewPager#SCROLL_STATE_DRAGGING   拖拽状态
             * @see ViewPager#SCROLL_STATE_SETTLING   中间状态
             */
            @Override
            public void onPageScrollStateChanged(int state) {
//                switch (state){
//                    case ViewPager.SCROLL_STATE_IDLE:
//                        Log.d(TAG, "SCROLL_STATE_IDLE");
//                        break;
//                    case ViewPager.SCROLL_STATE_DRAGGING:
//                        Log.d(TAG, "SCROLL_STATE_DRAGGING");
//                        break;
//                    case ViewPager.SCROLL_STATE_SETTLING:
//                        Log.d(TAG, "SCROLL_STATE_SETTLING");
//                        break;
//                }
            }
        });
    }

    private PagerAdapter adapter = new PagerAdapter() {
        /**
         * 获取Viewpager的个数
         * @return
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 是否要显示当前条目
         * @param view   是否要显示的条目
         * @param object  标记
         * @return 要显示true   不显示false
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化Viewpager的一个条目
         * @param container   ViewPager 本身
         * @param position   当前的位置
         * @return   返回的是条目的对象
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //防止索引越界
            position = position % imageViews.length;
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(imageViews[position]);
            //添加到容器中
            container.addView(imageView);
            return imageView;
        }

        /**
         * 要销毁的界面
         * @param container  ViewPager 本身
         * @param position    要销毁界面的位置
         * @param object    要销毁界面的标记(instantiateItem返回)
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    };
}
