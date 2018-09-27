package com.jiang.dlj.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.entity.DJGetChk_Entity;
import com.jiang.dlj.utils.LogUtil;
import com.jiang.dlj.utils.TabToast;
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
            viewHolder.test_1_view = view.findViewById(R.id.item_test_1);
            viewHolder.test_2_view = view.findViewById(R.id.item_test_2);
            viewHolder.title = view.findViewById(R.id.item_test_name);
            viewHolder.message = view.findViewById(R.id.item_test_message);
            viewHolder.read = view.findViewById(R.id.item_test_1_read);
            viewHolder.log = view.findViewById(R.id.item_test_1_log);
            viewHolder.edit = view.findViewById(R.id.item_test_1_context);
            viewHolder.unit_code = view.findViewById(R.id.item_test_1_unit_code);
            viewHolder.listViewForScrollView = view.findViewById(R.id.item_test_list);
            viewHolder.radioGroup = view.findViewById(R.id.item_test_radiogroup);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DJGetChk_Entity.ResultBean.ChkDetailsBean bean = beans.get(i);

        viewHolder.title.setText(bean.getParam_name());

        viewHolder.read.setOnClickListener(v -> TabToast.makeText("开发中"));
        viewHolder.log.setOnClickListener(v ->
                        //查询历史
                        TabToast.makeText("开发中")
//                new Get_History_Servlet((Activity) context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, bean.getTaskdetail_guid())
        );

        viewHolder.edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //定性特有
        if (bean.getOption() != null) {
            viewHolder.test_1_view.setVisibility(View.GONE);
            viewHolder.test_2_view.setVisibility(View.VISIBLE);

            viewHolder.radioGroup.removeAllViews();
            for (DJGetChk_Entity.ResultBean.ChkDetailsBean.OptionBean optionBean : bean.getOption()) {
                RadioButton radioButton = new RadioButton(context);
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                radioButton.setLayoutParams(layoutParams);
                radioButton.setText(optionBean.getName_tx());
                radioButton.setId(optionBean.getItem_id());

                radioButton.setChecked(bean.getRun_state() == optionBean.getAlmlevel_cd());

                radioButton.setOnCheckedChangeListener((compoundButton, b) -> {
                    bean.setOption_cd(optionBean.getAlmlevel_cd());
                    LogUtil.e(TAG, radioButton.getId());
//                    notifyDataSetChanged();
                });
                viewHolder.radioGroup.addView(radioButton);
            }

        } else {
            //定量
            viewHolder.test_1_view.setVisibility(View.VISIBLE);
            viewHolder.test_2_view.setVisibility(View.GONE);
            viewHolder.title.setText(bean.getParam_name());
            viewHolder.message.setText("区间(" + bean.getUnit_code() + "):" + bean.getMin_val() + "～" + bean.getMax_val());
            viewHolder.unit_code.setText(bean.getUnit_code());
        }

        return view;
    }

    class ViewHolder {
        LinearLayout test_1_view, test_2_view;
        TextView title, message, unit_code;
        Button read, log;
        EditText edit;
        ListViewForScrollView listViewForScrollView;
        RadioGroup radioGroup;
    }

}
