package com.jiang.dlj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.DJGetChk_Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/25
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO:
 */
public class Inspect_Adapter extends BaseAdapter {
    private static final String TAG = "Inspect_Adapter";

    Context context;

    List<DJGetChk_Entity.ResultBean> beans = new ArrayList<>();

    public Inspect_Adapter(Context context) {
        this.context = context;
    }

    public void setBeans(List<DJGetChk_Entity.ResultBean> beans) {
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return beans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.item_inspect, null);
            viewHolder.name = view.findViewById(R.id.item_inspect_name);
            viewHolder.state = view.findViewById(R.id.item_inspect_state);
            viewHolder.message = view.findViewById(R.id.item_inspect_message);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DJGetChk_Entity.ResultBean bean = beans.get(i);

        if (bean.getChkDetails().get(0) != null) {
            switch (bean.getChkDetails().get(0).getState()) {
                case 0:
                    viewHolder.state.setVisibility(View.GONE);
                    break;
                case 2:
                    viewHolder.state.setVisibility(View.VISIBLE);
                    viewHolder.state.setImageResource(R.drawable.ic_complete);
                    break;
            }
        }
        viewHolder.name.setText(bean.getDjchk_name());

        return view;
    }

    class ViewHolder {
        ImageView state;
        TextView name, message;
    }
}
