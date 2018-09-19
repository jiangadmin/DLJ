package com.jiang.dlj.iris.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.dialog.Base_Dialog;
import com.jiang.dlj.iris.Dictionary;
import com.jiang.dlj.iris.SqliteDataBase;
import com.jiang.dlj.utils.TabToast;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ShowUserInfoRecyclerViewAdapter extends RecyclerView.Adapter<ShowUserInfoRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Map<String, String>> datas;
    private SqliteDataBase sqliteDataBase;
    private String oldName;
    private int identifyOrderSelected = -2;
    private int continueOrderSelected = -3;
    private int endOrderSelected = -4;
    private ObjectAnimator scaleAnim;
    boolean isActionEnabled;

    public ShowUserInfoRecyclerViewAdapter(Context context, List<Map<String, String>> datas) {
        this.context = context;
        this.datas = datas;
        sqliteDataBase = SqliteDataBase.getInstance(context);
        identifyOrderSelected = -2;
        continueOrderSelected = -3;
        endOrderSelected = -4;
    }

    /**
     * 传入需要伸缩动画的Order
     *
     * @param orderSelected
     */
    public void setIdentifySelectOrder(int orderSelected) {
        this.identifyOrderSelected = orderSelected;
        notifyDataSetChanged();
    }

    public void setContinueSelectOrder(int orderSelected) {
        this.continueOrderSelected = orderSelected;
    }

    public void setContinueSelectOrderColor(int orderSelected) {
        this.endOrderSelected = orderSelected;
        notifyDataSetChanged();
    }

    public void setActionEnabled(boolean enabled) {
        this.isActionEnabled = enabled;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_user_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item_tv_userName.setText(datas.get(position).get("name"));

        String order = datas.get(position).get("order");
        int oderIndex = 0;
        if (order != null) {
            try {
                oderIndex = Integer.parseInt(order);
            } catch (Exception e) {
                oderIndex = 0;
            }
        }
        if (oderIndex < 0 || oderIndex > Dictionary.ARR_USER_ICON.length - 1) {
            oderIndex = 0;
        }

        if (oderIndex == identifyOrderSelected) {
            holder.itemView.getOnFocusChangeListener();
            TabToast.makeText(datas.get(position).get("name"));
        }

        if (oderIndex == continueOrderSelected) {
            scaleAnim = ObjectAnimator.ofFloat(holder.itemView, "ScaleX", 1.0f, 1.2f, 1.0f);
            scaleAnim.setDuration(60);
            scaleAnim.start();
        }

        if (oderIndex == endOrderSelected) {
            holder.itemView.setBackgroundColor(Color.parseColor("#dddddd"));
        }

        holder.item_iv_favicon.setImageResource(Dictionary.ARR_USER_ICON[oderIndex]);

        holder.item_ll_delete.setOnClickListener(new ActionListener(0, position));
        holder.item_ll_rename.setOnClickListener(new ActionListener(1, position));
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_iv_favicon;
        TextView item_tv_userName;
        ImageView item_iv_delete;
        ImageView item_iv_rename;

        LinearLayout item_ll_delete;
        LinearLayout item_ll_rename;

        RelativeLayout mPlayRelativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            item_iv_favicon = itemView.findViewById(R.id.item_iv_user_favicon);
            item_tv_userName = itemView.findViewById(R.id.item_tv_user_name);
            item_iv_delete = itemView.findViewById(R.id.item_iv_delete_user_info);
            item_iv_rename = itemView.findViewById(R.id.item_iv_rename_user_info);
            item_ll_delete = itemView.findViewById(R.id.ll_item_iv_delete_user_info);
            item_ll_rename = itemView.findViewById(R.id.ll_item_iv_rename_user_info);
            mPlayRelativeLayout = itemView.findViewById(R.id.rootLayout);
        }
    }

    public class ActionListener implements View.OnClickListener {
        final int flag;
        int position;

        public ActionListener(int flag, int position) {
            this.flag = flag;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (!isActionEnabled) {
                view.setPressed(false);
                return;
            }
            if (flag == 0) {
                doDelete();
            }
            if (flag == 1) {
                doUpdate();
            }
        }

        void doDelete() {
            Base_Dialog dialog = new Base_Dialog(context);
            dialog.setMessage("确定删除此用户吗？");
            dialog.setEsc("取消", null);
            dialog.setOk("删除", v -> {
                sqliteDataBase.removeByFavicon(Integer.parseInt(datas.get(position).get("order")));
                Log.e("tony", "adapter ***position==" + position);
                datas.remove(position);
                Log.e("tony", "doDelete datas:" + datas);
                notifyDataSetChanged();
            });
        }

        void doUpdate() {
            oldName = datas.get(position).get("name");

            String newName = "新名字";
            //TODO:改名字
            sqliteDataBase.updateNameByFavicon(newName, Integer.parseInt(datas.get(position).get("order")));
            datas.get(position).put("name", newName);
            notifyDataSetChanged();

        }
    }
}
