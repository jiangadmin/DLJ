package com.jiang.dlj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.Icon;

import java.util.ArrayList;

/**
 * @author: jiangadmin
 * @date: 2018/9/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */
public class Home_Item_Adapter extends BaseAdapter {
    private ArrayList<Icon> mData = new ArrayList<>();

    Context context;

    public void setmData(ArrayList<Icon> mData) {
        this.mData = mData;
    }

    public Home_Item_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_grid_icon, null);
            viewHolder.icon =  convertView.findViewById(R.id.img_icon);
            viewHolder.tv =  convertView.findViewById(R.id.txt_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Icon icon = mData.get(position);
        viewHolder.tv.setText(icon.getiName());
        viewHolder.icon.setImageResource(icon.getiId());
        return convertView;
    }


    class  ViewHolder{
        private ImageView icon;
        TextView tv;
    }

}
