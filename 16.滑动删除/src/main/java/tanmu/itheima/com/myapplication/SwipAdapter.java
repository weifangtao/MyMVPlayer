package tanmu.itheima.com.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 魏方涛 on 2017/1/4.
 */

public class SwipAdapter extends BaseAdapter {
    private ArrayList<String> datas;

    public SwipAdapter(ArrayList<String> datas) {

        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public String getItem(int position) {
        if (datas != null) {
            return datas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_list_swip, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.leftText.setText(datas.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                datas.remove(position); 会出现一直打开
                //先关闭在删除
                //获取父容器
                MySwipView mySwipView = (MySwipView) v.getParent();
                //触发重新布局
                mySwipView.requestLayout();

                datas.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {

        private final TextView leftText;
        private final TextView button;

        public ViewHolder(View view) {
            leftText = (TextView) view.findViewById(R.id.leftText);
            button = (TextView) view.findViewById(R.id.button);
        }
    }
}
