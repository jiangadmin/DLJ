package com.jiang.dlj.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.DJIdPosByGuids_Entity;

import java.util.ArrayList;
import java.util.List;


public class DJIdPos_Adapter extends android.widget.BaseAdapter {
    private static final String TAG = "Adapter_Help_1";
    private Activity context;
    private List<DJIdPosByGuids_Entity.ResultBean> listData = new ArrayList<>();

    public DJIdPos_Adapter(Activity context) {
        this.context = context;
    }

    public void setListData(List<DJIdPosByGuids_Entity.ResultBean> listData) {
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
            convertView = View.inflate(context, R.layout.item_djidpos, null);
            viewHolder.no = convertView.findViewById(R.id.item_djidpos_no);
            viewHolder.message = convertView.findViewById(R.id.item_djidpos_message);
            viewHolder.state = convertView.findViewById(R.id.item_djidpos_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        DJIdPosByGuids_Entity.ResultBean bean = listData.get(position);
        viewHolder.no.setText(String.format("位置编号：%s", bean.getId_cd()));
        viewHolder.message.setText(String.format("描述：%s", bean.getIdpos_name()));

        switch (bean.getState()) {
            case 0:
                viewHolder.state.setText("未巡检");
                viewHolder.state.setTextColor(context.getResources().getColor(R.color.style_color_6));
                break;
            case 1:
                viewHolder.state.setText("巡检中");
                viewHolder.state.setTextColor(context.getResources().getColor(R.color.style_color_5));
                break;
            case 2:
                viewHolder.state.setText("已巡检");
                viewHolder.state.setTextColor(context.getResources().getColor(R.color.style_color_1));
                break;
        }


        return convertView;
    }

    class ViewHolder {
        TextView no, message, state;
    }
}
