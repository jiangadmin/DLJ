package com.jiang.dlj.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.DJLine_Entity;
import com.jiang.dlj.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;


public class DJLine_Adapter extends android.widget.BaseAdapter {
    private static final String TAG = "Adapter_Help_1";
    private Activity context;
    private List<DJLine_Entity.ResultBean> listData = new ArrayList<>();

    public DJLine_Adapter(Activity context) {
        this.context = context;
    }

    public void setListData(List<DJLine_Entity.ResultBean> listData) {
        this.listData = listData;
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
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
            convertView = View.inflate(context, R.layout.item_djline, null);
            viewHolder.title = convertView.findViewById(R.id.item_djline_title);
            viewHolder.time = convertView.findViewById(R.id.item_djline_time);
            viewHolder.surplus = convertView.findViewById(R.id.item_djline_surplus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DJLine_Entity.ResultBean bean = listData.get(position);
        viewHolder.title.setText(bean.getLinename_tx());
        viewHolder.time.setText(String.format("开始时间：%s\n结束时间：%s", bean.getBegintime(), bean.getEndtime()));
        String surplus = ToolUtils.TimeInterval(bean.getBegintime(), bean.getEndtime());
        viewHolder.surplus.setText(surplus);
        switch (surplus) {
            case "未开始":
                viewHolder.surplus.setTextColor(context.getResources().getColor(R.color.style_color_5));
                break;
            case "已超时":
                viewHolder.surplus.setTextColor(context.getResources().getColor(R.color.style_color_6));
                break;
            default:
                viewHolder.surplus.setTextColor(context.getResources().getColor(R.color.gray_4));
                break;
        }

        return convertView;
    }

    class ViewHolder {
        TextView title, time, surplus;
    }
}
