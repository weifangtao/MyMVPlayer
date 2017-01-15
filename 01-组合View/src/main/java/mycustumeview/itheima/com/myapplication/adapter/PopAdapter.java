package mycustumeview.itheima.com.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mycustumeview.itheima.com.myapplication.R;

/**
 * Created by 魏方涛 on 2017/1/1.
 */

public class PopAdapter extends BaseAdapter {
    private final ArrayList<String> datas;
    private OndeleteListener listener;

    public PopAdapter(ArrayList<String> datas) {
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
            convertView = View.inflate(parent.getContext(), R.layout.item_pop, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(datas.get(position));
        //设置删除的点击事件
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDelete(datas.get(position));
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        private final ImageView image;
        private final TextView title;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.delete);
        }
    }

    public interface OndeleteListener {

        void onDelete(String s);
    }

    public void setOndeleteListener(OndeleteListener listener) {

        this.listener = listener;
    }
}
