package com.jiang.dlj.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jiang.dlj.R;
import com.jiang.dlj.utils.LogUtil;

/**
 * @author: jiangyao
 * @date: 2017/11/30.
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: 运行状态
 */

public class Choose_Run_State_Dialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "Base_Dialog";

    Button state_0, state_1, state_2, esc;

    TextView textView;

    public Choose_Run_State_Dialog(@NonNull Context context,TextView textView1) {
        super(context, R.style.myDialogTheme);
        textView =textView1;
        try {
            show();
        } catch (Exception e) {

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //把状态栏设置为透明

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //透明状态栏
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.dialog_run_state);

        initview();

    }


    private void initview() {
        state_0 = findViewById(R.id.dialog_run_state_0);
        state_1 = findViewById(R.id.dialog_run_state_1);
        state_2 = findViewById(R.id.dialog_run_state_2);
        esc = findViewById(R.id.dialog_esc);

        esc.setOnClickListener(this);

        state_0.setOnClickListener(this);
        state_1.setOnClickListener(this);
        state_2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dialog_run_state_0:
                textView.setText("运行");
                break;
            case R.id.dialog_run_state_1:
                textView.setText("检修");
                break;
            case R.id.dialog_run_state_2:
                textView.setText("停运");
                break;

        }
        dismiss();
    }
}
