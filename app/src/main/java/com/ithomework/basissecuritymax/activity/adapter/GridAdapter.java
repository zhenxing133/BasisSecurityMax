package com.ithomework.basissecuritymax.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ithomework.basissecuritymax.R;

/**
 * Created by Administrator on 2017/7/23.
 */

public class GridAdapter extends BaseAdapter {
    int[] imageId = { R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
            R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
            R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings };
    String[] names = { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
            "高级工具", "设置中心" };
    private Context mContext;
    public GridAdapter(Context applicationContext) {
        mContext = applicationContext ;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = View.inflate(mContext, R.layout.main_grid_item, null);
            holder.img = (ImageView) convertView.findViewById(R.id.gridview_icon);
            holder.tv = (TextView) convertView.findViewById(R.id.gridview_name);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.img.setImageResource(imageId[position]);
        holder.tv.setText(names[position]);
        return convertView;
    }

    class MyViewHolder {
        ImageView img;
        TextView tv;
    }
}
