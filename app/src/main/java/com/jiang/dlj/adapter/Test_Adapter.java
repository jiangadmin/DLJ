package com.jiang.dlj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.DJGetChk_Entity;
import com.jiang.dlj.utils.ToolUtils;
import com.jiang.dlj.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: jiangyao
 * @date: 2018/9/22
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 测项
 */
public class Test_Adapter extends BaseAdapter {
    private static final String TAG = "Test_Adapter";

    Context context;

    List<DJGetChk_Entity.ResultBean.ChkDetailsBean> beans = new ArrayList<>();

    public Test_Adapter(Context context) {
        this.context = context;
    }

    public void setBeans(List<DJGetChk_Entity.ResultBean.ChkDetailsBean> beans) {
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
            view = View.inflate(context, R.layout.item_test, null);
            viewHolder.title = view.findViewById(R.id.item_test_name);
            viewHolder.listViewForScrollView = view.findViewById(R.id.item_test_list);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DJGetChk_Entity.ResultBean.ChkDetailsBean bean = beans.get(i);

        viewHolder.title.setText(bean.getParam_name());
        //定性特有
        if (bean.getOption() != null) {
            Test_Item_Adapter test_item_adapter = new Test_Item_Adapter(context);
            test_item_adapter.setBeans(bean.getOption());
            test_item_adapter.setDetailsBean(bean);
            viewHolder.listViewForScrollView.setAdapter(test_item_adapter);
        }

        return view;
    }

    class ViewHolder {
        TextView title;
        ListViewForScrollView listViewForScrollView;
    }


    class Test_Item_Adapter extends BaseAdapter {

        Context context;

        public Test_Item_Adapter(Context context) {
            this.context = context;
        }

        DJGetChk_Entity.ResultBean.ChkDetailsBean detailsBean;
        List<DJGetChk_Entity.ResultBean.ChkDetailsBean.OptionBean> beans = new ArrayList<>();

        public void setBeans(List<DJGetChk_Entity.ResultBean.ChkDetailsBean.OptionBean> beans) {
            this.beans = beans;
        }

        public void setDetailsBean(DJGetChk_Entity.ResultBean.ChkDetailsBean detailsBean) {
            this.detailsBean = detailsBean;
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
                view = View.inflate(context, R.layout.item_test_item, null);
                viewHolder.list = view.findViewById(R.id.item_test_item);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.list.removeAllViews();

            RadioButton radioButton = new RadioButton(context);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.height = ToolUtils.dp2px(40);
            radioButton.setId((int) Math.random() * 100);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(beans.get(i).getName_tx());
            viewHolder.list.addView(radioButton);
            radioButton.setOnCheckedChangeListener((compoundButton, b) -> detailsBean.setState(beans.get(i).getAlmlevel_cd()));

            return view;
        }

        class ViewHolder {
            RadioGroup list;
        }

    }

}
